package kg.parking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.parking.entity.ParkingPlace;
import kg.parking.repository.ParkingPlaceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private ParkingPlaceRepository parkingPlaceRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAvailablePlaces() throws Exception {
        List<ParkingPlace> list = new ArrayList<>(2);
        list.add(new ParkingPlace(1L, "A 1", false, false, 10L));
        list.add(new ParkingPlace(2L, "B 1", false, false, 15L));
        when(parkingPlaceRepository.findAllByIsBookedFalseAndIsTakenFalse()).thenReturn(list);

        mockMvc.perform(get("/parking/v1/parkingPlace/freePlaces").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].position").value("A 1"))
                .andExpect(jsonPath("$[1].position").value("B 1"))
                .andReturn();
    }
}
