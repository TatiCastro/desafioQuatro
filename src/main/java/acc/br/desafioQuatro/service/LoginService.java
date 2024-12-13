package acc.br.desafioQuatro.service;

import acc.br.desafioQuatro.model.Login;
import acc.br.desafioQuatro.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public Login register(Login login) {
        return loginRepository.save(login);
    }

    public Login findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }
}
