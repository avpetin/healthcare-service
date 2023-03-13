package ru.netology.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;

public class MedicalServiceTests {
    @Test
    public void checkBloodPressureTest(){
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        HealthInfo healthInfo = Mockito.mock(HealthInfo.class);
        Mockito.when(healthInfo.getBloodPressure())
                .thenReturn(new BloodPressure(120, 80));
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        PatientInfo patientInfo = new PatientInfo(null, null, null, null, healthInfo);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(patientInfo);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);

        medicalService.checkBloodPressure(Mockito.anyString(), new BloodPressure(120, 80));

        Mockito.verify(sendAlertService, Mockito.times(1));
    }

    @Test
    public void checkTemperatureTest(){
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        HealthInfo healthInfo = Mockito.mock(HealthInfo.class);
        Mockito.when(healthInfo.getNormalTemperature())
                .thenReturn(new BigDecimal(36.6));
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        PatientInfo patientInfo = new PatientInfo(null, null, null, null, healthInfo);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(patientInfo);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature(Mockito.anyString(), new BigDecimal(36.6));

        Mockito.verify(sendAlertService,
                Mockito.times(1));
    }

    @Test
    public void bloodPressureArgumentCaptureTest(){
        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);
        HealthInfo healthInfo = Mockito.mock(HealthInfo.class);
        Mockito.when(healthInfo.getBloodPressure())
                .thenReturn(new BloodPressure(120, 80));
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        PatientInfo patientInfo = new PatientInfo(null, null, null, null, healthInfo);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(patientInfo);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        medicalService.checkBloodPressure(Mockito.anyString(), new BloodPressure(140, 90));

        Mockito.verify(sendAlertService, Mockito.times(1)).send(argumentCaptor.capture());
        Assertions.assertEquals(String.format("Warning, patient with id: %s, need help", patientInfo.getId()),
                argumentCaptor.getValue());
    }
}