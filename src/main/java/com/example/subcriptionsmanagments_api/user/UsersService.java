package com.example.subcriptionsmanagments_api.user;

import com.example.subcriptionsmanagments_api.globalExceptionHandler.UserNotFoundException;
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

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public Users createUser(Users user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Uzytkownik musi posiadac nazwe");
        }
        return usersRepository.save(user);
    }

    public Users updateByPutUser(long id, Users newUser) {
        return usersRepository.findById(id).map(users -> {
            users.setUsername(newUser.getUsername());
            users.setEmail(newUser.getEmail());
            users.setPassword(newUser.getPassword());
            return usersRepository.save(users);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        if (!usersRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        usersRepository.deleteById(id);
    }

}


//nakładka na kalendarz, która wyświetla od której do której godziny sala
// jest wolna, zeby było wiadomo od której do której godziny sala jest zajeta
// frontend webowy, zeby byla mozliwosc zarzadzania i ustawianiem tych spotkan


