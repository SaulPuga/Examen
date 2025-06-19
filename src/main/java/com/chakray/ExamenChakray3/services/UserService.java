package com.chakray.ExamenChakray3.services;

import com.chakray.ExamenChakray3.models.AddressModel;
import com.chakray.ExamenChakray3.models.UserModel;
import com.chakray.ExamenChakray3.repositories.IAddressRepository;
import com.chakray.ExamenChakray3.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IAddressRepository iAddressRepository;

    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }

    public List<UserModel> getByParam(String param){
        return userRepository.findAll(Sort.by(param));
    }

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public UserModel UpdateAddress(UserModel request, Long id){
        UserModel address = userRepository.findById(id).get();
        address.setAddress(request.getAddress());
        return address;
    }

    public Boolean deleteUser (Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ArrayList<AddressModel> addressById (long id){
        Optional<UserModel> optionalEntity = userRepository.findById(id);
        UserModel entity = optionalEntity.get();
        ArrayList<AddressModel> address = entity.getAddress();
        return address;
    }

    public AddressModel updateAddresbyid(Long userId, Long addressId, AddressModel addresDetails ) {

        ArrayList<AddressModel> listaDirecciones = addressById(userId);
        /*UserModel user = userRepository.findById(userId).get();
        if(user.getId() == userId){
            System.out.println("usuario encontrado");
        }else{
            System.out.println("usuario no encontrado");
        }*/
        AddressModel addres = iAddressRepository.findById(addressId).get();

        addres.setId(addresDetails.getId());
        addres.setName(addresDetails.getName());
        addres.setStreet(addresDetails.getStreet());
        addres.setCountry_code(addresDetails.getCountry_code());

        return iAddressRepository.save(addres);
    }
}
