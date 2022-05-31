package com.example.demomicroservicestudent.service;

import com.example.demomicroservicestudent.entity.Student;
import com.example.demomicroservicestudent.feignClient.AddressFeignClient;
import com.example.demomicroservicestudent.repository.StudentRepository;
import com.example.demomicroservicestudent.request.CreateStudentRequest;
import com.example.demomicroservicestudent.response.AddressResponse;
import com.example.demomicroservicestudent.response.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

	Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CommonService commonService;


	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());

		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);

		StudentResponse studentResponse = new StudentResponse(student);

		//studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

		studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));


		return studentResponse;
	}
	
	public StudentResponse getById (long id) {

		logger.info("Inside the student getById methode in studentService");

		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);
		//studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
		studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

		return studentResponse;
	}

	/*
	WON'T WORK BECAUSE IT INTERNALLY USES AOP-> if you are calling the method in the same class, it won't work!

	@CircuitBreaker(name = "demoMicroserviceAddress", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById(long addressId){
		//mono is in the reactive programming
		//Mono<AddressResponse> addressResponseMono = webClient.get().uri("/getById/" + addressId)
				//.retrieve().bodyToMono(AddressResponse.class);

		//return addressResponseMono.block();

		AddressResponse addressResponse = addressFeignClient.getById(addressId);

		return addressResponse;
	}

	public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable){

		return new AddressResponse();
	}

	 */
}
