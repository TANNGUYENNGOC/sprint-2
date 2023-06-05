package com.car_management.controller;

import com.car_management.dto.history.IHistoryDTO;
import com.car_management.dto.history.IHistoryDTO1;
import com.car_management.model.user.User;
import com.car_management.service.oder.IOderService;
import com.car_management.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api-user")
@CrossOrigin
public class UserRestController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOderService iOderService;

    @GetMapping("get-user")
    public ResponseEntity<User> getUser (@RequestParam(defaultValue = "") Integer idUser){
        User user = iUserService.findById(idUser);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<Page<IHistoryDTO>> getHistory(@RequestParam(defaultValue = "") Integer idUser,
                                                        @PageableDefault(value = 4)Pageable pageable){
        Page<IHistoryDTO> iHistoryDTOS = iOderService.getHistory(idUser,pageable);
        if (iHistoryDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iHistoryDTOS,HttpStatus.OK);
    }

    @GetMapping("/historyDetail")
    public ResponseEntity<Page<IHistoryDTO>> getHistoryDetail(@RequestParam(defaultValue = "") Integer idOderDetail){
        List<IHistoryDTO1> iHistoryDTOS = iOderService.getHistory1(idOderDetail);
        if (iHistoryDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(iHistoryDTOS,HttpStatus.OK);
    }


}
