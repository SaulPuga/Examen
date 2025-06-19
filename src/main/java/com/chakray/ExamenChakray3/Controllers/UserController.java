package com.chakray.ExamenChakray3.Controllers;

import com.chakray.ExamenChakray3.models.AddressModel;
import com.chakray.ExamenChakray3.models.UserModel;
import com.chakray.ExamenChakray3.services.SHA1Service;
import com.chakray.ExamenChakray3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SHA1Service sha1Service;

    @GetMapping("/user")
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping("/users")
    public UserModel saveUser(@RequestBody UserModel user){
        String sha1Hash = sha1Service.sha1(user.getPassword());
        user.setPassword(sha1Hash);
        return this.userService.saveUser(user);
    }

    @GetMapping(path= "/users/{id}")
    public Optional<UserModel> getUserbyId(@PathVariable("id") Long id){
        return this.userService.getById(id);
    }

    /*@PutMapping(path = "/users/{id}/addresses/{address_id}")
    public UserModel updateAddress(@RequestBody UserModel request, @PathVariable("id")Long id){
        return this.userService.UpdateAddress(request, id);
    }*/

    @DeleteMapping(path = "/users/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean delete = this.userService.deleteUser(id);
        if(delete){
            return "Remove user with id " + id;
        }else{
            return "ERROR for delete user whith id " + id;
        }
    }
    @GetMapping(path= "/users/{id}/addresses")
    public ArrayList<AddressModel> getAddresByIdUser(@PathVariable("id") Long id){
        return this.userService.addressById(id);
    }

    @GetMapping(path= "/users")
    public ResponseEntity<List<UserModel>> getUserbyParam(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "created", required = false) Date created,
            String sortedBy){
        List<UserModel> user = userService.getByParam(sortedBy);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/users/{userId}/addresses/{addressId}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable Long userId,
                                                      @PathVariable Long addressId,
                                                      @RequestBody  AddressModel addressDetails){

        AddressModel updatedAddress = userService.updateAddresbyid(userId, addressId, addressDetails);
        return ResponseEntity.ok(updatedAddress);
    }


}
