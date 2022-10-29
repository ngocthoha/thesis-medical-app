package com.thesis.medicalapp.repository;

import com.thesis.medicalapp.models.Profile;
import com.thesis.medicalapp.models.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QARepository extends JpaRepository<QuestionAnswer, String> {

}
