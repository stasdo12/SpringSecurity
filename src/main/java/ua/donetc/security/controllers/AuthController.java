package ua.donetc.security.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.donetc.security.dto.AuthDTO;
import ua.donetc.security.dto.PersonDTO;
import ua.donetc.security.models.Peronsq;
import ua.donetc.security.security.JWTUtil;
import ua.donetc.security.services.RegistrationService;
import ua.donetc.security.util.PersonValidator;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;


    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator,
                          JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;

    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                   BindingResult bindingResult) {
        Peronsq person = convertToPerson(personDTO);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("message", "Ошибка!");
        }

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {

        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    public Peronsq convertToPerson(PersonDTO personDTO) {
        return this.modelMapper.map(personDTO, Peronsq.class);
    }
}