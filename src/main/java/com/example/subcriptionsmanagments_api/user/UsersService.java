package com.example.subcriptionsmanagments_api.user;

import com.example.subcriptionsmanagments_api.globalExceptionHandler.ApiException;
import com.example.subcriptionsmanagments_api.globalExceptionHandler.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersResponse> getAllUsers() {
        return usersRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public UsersResponse getUserById(Long id) {
       Users user = usersRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND, id));
        return mapToResponse(user);
    }

    public UsersResponse createUser(UsersRequest request) {
        Users user = new Users();
        Users savedUser = getUser(request, user);
        return mapToResponse(savedUser);
    }

    public UsersResponse updateUser(long id, UsersRequest request) {

        Users user = usersRepository.findById(id)
        .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND, id));
        Users updatedUser = getUser(request, user);
        return mapToResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!usersRepository.existsById(id)) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND, id);
        }
        usersRepository.deleteById(id);
    }


    private Users getUser(UsersRequest request, Users user) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return usersRepository.save(user);
    }

    public UsersResponse mapToResponse(Users user) {
        return new UsersResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}


//nakładka na kalendarz, która wyświetla od której do której godziny sala
// jest wolna, zeby było wiadomo od której do której godziny sala jest zajeta
// frontend webowy, zeby byla mozliwosc zarzadzania i ustawianiem tych spotkan


