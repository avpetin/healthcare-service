package ru.netology;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import static org.mockito.Mockito.*;

public class MedicalServiceTests {
    PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
    SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
    @Test
    public void checkBloodPressureTest(){
        BloodPressure bloodPressure = Mockito.mock(BloodPressure.class);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        doAnswer()
        Mockito.verify(medicalService.checkBloodPressure(Mockito.anyString(), bloodPressure), Mockito.times(1));
    }

    @Test
    public void checkTemperatureTest(){

    }

    @Test
    public void medicalServiceTestsWithMockito(){

    }
}