package com.pwojczyn.HostStats.controllers;

import com.pwojczyn.HostStats.models.forms.repositories.HostInfoRepository;
import com.pwojczyn.HostStats.models.forms.repositories.HostsRepository;
import com.pwojczyn.HostStats.models.forms.repositories.UserRepository;
import com.pwojczyn.HostStats.services.HostInfoModel;
import com.pwojczyn.HostStats.services.HostsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
public class RestController {
    @Autowired
    HostInfoRepository hostInfoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HostsRepository hostsRepository;

    @RequestMapping(value = "/rest/hostinfo/apikey/{key}/hostkey/{hostkey}/", method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity reservation(@RequestBody HostInfoModel model,@PathVariable("key") String apikey,@PathVariable("hostkey") String hostKey){
        int userId = userRepository.findByApikey(apikey).getId();
        // first time host
        if (userRepository.existsByapikey(apikey)&& !hostsRepository.existsByhostId(hostKey)) {
            model.setHostId(hostKey);
            model.setUserId(userId);
            hostInfoRepository.save(model);
            hostsRepository.save(new HostsModel(userId, hostKey));
        }

        if (userRepository.existsByapikey(apikey) && hostsRepository.existsByhostId(hostKey) && !hostInfoRepository.existsByhostId(hostKey)){
            model.setHostId(hostKey);
            model.setUserId(userId);
            hostInfoRepository.save(model);
        }else if (userRepository.existsByapikey(apikey) && hostsRepository.existsByhostId(hostKey)){
            model.setHostId(hostKey);
            model.setId(hostInfoRepository.findByHostId(hostKey).getId());
            model.setUserId(hostsRepository.findByhostId(hostKey).getUserId());
            hostInfoRepository.save(model);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

/*
 @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(value = "/rest/reservation", method = RequestMethod.GET,
    produces = "application/json")
    public ResponseEntity reservationIndex(@RequestHeader("Key") String key){
        if(apiKeysRepository.existsByTextEquals(key)){
            ApiKeysModel apiKeysModel = apiKeysRepository.findByText(key);

            if(apiKeysModel.getCounter() >= 100){
                return new ResponseEntity("Too many users",HttpStatus.NOT_ACCEPTABLE);
            } else{
                apiKeysModel.setCounter(apiKeysModel.getCounter()+1);
                apiKeysRepository.save(apiKeysModel);
            }



        }
        return new ResponseEntity(reservationRepository.findAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/reservation/{lastname}", method = RequestMethod.GET,
    produces = "application/json")
    public ResponseEntity reservation(@PathVariable("lastname") String lastname){
        return new ResponseEntity(reservationRepository.findByLastnameIgnoreCase(lastname),HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/reservation", method = RequestMethod.POST,
    produces = "application/json")
    public ResponseEntity reservation(@RequestBody ReservationModel model){
        reservationRepository.save(model);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/reservation/{id}", method = RequestMethod.DELETE,
            produces = "application/json")
    public ResponseEntity reservation(@PathVariable("id") int id){
        reservationRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/reservation/{id}/{date}", method = RequestMethod.PUT,
            produces = "application/json")
    public ResponseEntity reservationDateChange(@PathVariable("id") int id,
                                                @PathVariable("date") String date){
        ReservationModel model = reservationRepository.findOne(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newDate = LocalDate.from(formatter.parse(date));

        if(reservationRepository.existsByDateEquals(newDate)){
            return new ResponseEntity("This date is busy",HttpStatus.CONFLICT);
        }

        model.setDate(LocalDate.from(formatter.parse(date)));
        reservationRepository.save(model);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/reservation", method = RequestMethod.PUT,
    produces = "application/json")
    public ResponseEntity responseAct(@RequestBody ReservationModel reservationModel){
        reservationRepository.save(reservationModel);
        return new ResponseEntity(HttpStatus.OK);
    }
    */
}
