/**
 * 
 */
package com.afour.emgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afour.emgmt.common.AppResponse;
import com.afour.emgmt.common.GenericResponse;
import com.afour.emgmt.model.UserDTO;
import com.afour.emgmt.service.OrganizerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This is a Controller class having all the REST (GET POST PUT DELETE) end
 * points to manage any Organizer.
 * 
 * @author Sandeep Jariya
 */
@RestController
@RequestMapping("/organizer")
@Api(tags = "Manage Organizers")
public class OrganizerController {

	@Autowired
	OrganizerService service;

	@Autowired
	MessageSource messages;

	@Autowired
	GenericResponse genericResponse;

	private AppResponse response;
	
	/* Get all the existing organizers without any filter */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Fetch all the organizers without any filter!")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found all the organizers!"),
			@ApiResponse(code = 204, message = "No data found!") })
	@GetMapping(value = "/organizers", produces = "application/json")
	public ResponseEntity<AppResponse> fetchAllOrganizers() {
		List<UserDTO> result = service.fetchAllOrganizers();
		response = new AppResponse();
		if (result == null)
			return new ResponseEntity(genericResponse.getNoDataFoundResponse(), HttpStatus.OK);

		return new ResponseEntity(genericResponse.getSuccessDataFoundResponse(result, result.size()), HttpStatus.OK);
	}
	
	/* Get one existing organizer using its id */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Fetch one organizer by ID!")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found the organizer!"),
			@ApiResponse(code = 204, message = "No data found!") })
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<AppResponse> findOrganizerByID(@PathVariable(value = "id") final Integer id) {
		if (null == id)
			return new ResponseEntity(genericResponse.getEmtyRequestResponse(), HttpStatus.BAD_REQUEST);

		UserDTO result = service.findOrganizerByID(id);
		if (result == null)
			return new ResponseEntity(genericResponse.getNoDataFoundResponse(), HttpStatus.OK);

		return new ResponseEntity(genericResponse.getSuccessDataFoundResponse(result, 1), HttpStatus.OK);
	}
	
	/* Get one existing organizer using its username */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Fetch one organizers by USERNAME!")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found the organizer!"),
			@ApiResponse(code = 204, message = "No data found!") })
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<AppResponse> findOrganizerByUserName(
			@RequestParam(value = "userName") final String userName) {
		if (null == userName)
			return new ResponseEntity(genericResponse.getEmtyRequestResponse(), HttpStatus.BAD_REQUEST);

		UserDTO result = service.findOrganizerByUserName(userName);
		if (result == null)
			return new ResponseEntity(genericResponse.getNoDataFoundResponse(), HttpStatus.OK);

		return new ResponseEntity(genericResponse.getSuccessDataFoundResponse(result, 1), HttpStatus.OK);
	}
	
	/* Create a new organizer*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Create a new Organizer.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created!"),
			@ApiResponse(code = 400, message = "Bad Request!") })
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AppResponse> addOrganizer(@RequestBody UserDTO dto) {
		if (null == dto)
			return new ResponseEntity(genericResponse.getEmtyRequestResponse(), HttpStatus.BAD_REQUEST);

		UserDTO result = service.addOrganizer(dto);

		if (result == null)
			return new ResponseEntity(
					genericResponse.getRequestFailResponse("organizer.create.fail", new UserDTO[] { dto }),
					HttpStatus.BAD_REQUEST);

		response = genericResponse.getRequestSuccessResponse("organizer.create.success", result, HttpStatus.CREATED);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	/* update an existing organizer */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Update an Organizer.")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted and Updated!"),
			@ApiResponse(code = 400, message = "Bad Request!") })
	@PutMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AppResponse> updateOrganizer(@RequestBody UserDTO dto) {
		if (null == dto)
			return new ResponseEntity(genericResponse.getEmtyRequestResponse(), HttpStatus.BAD_REQUEST);

		UserDTO result = service.updateOrganizer(dto);

		if (result == null)
			return new ResponseEntity(genericResponse.getRequestFailResponse("organizer.update.fail",
					new Integer[] { dto.getUserId() }), HttpStatus.BAD_REQUEST);

		response = genericResponse.getRequestSuccessResponse("organizer.update.success", result, HttpStatus.CREATED);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	/* Delete one organizer using its id */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "Delete an Organizer.")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Deleted the requested organizer!"),
			@ApiResponse(code = 400, message = "Bad Request!") })
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<AppResponse> deleteOrganizer(@PathVariable(value = "id") final Integer id) {
		if (null == id)
			return new ResponseEntity(genericResponse.getEmtyRequestResponse(), HttpStatus.BAD_REQUEST);

		Boolean result = service.deleteOrganizerByID(id);
		if (result == null)
			return new ResponseEntity(
					genericResponse.getRequestFailResponse("organizer.delete.fail", new Integer[] { id }),
					HttpStatus.BAD_REQUEST);

		response = genericResponse.getRequestSuccessResponse("organizer.delete.success", result, HttpStatus.ACCEPTED);
		return new ResponseEntity(response, HttpStatus.OK);
	}

}
