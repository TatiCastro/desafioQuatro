package acc.br.desafioQuatro.service;

import acc.br.desafioQuatro.model.Estudante;
import acc.br.desafioQuatro.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService {

    @Autowired
    EstudanteRepository estudanteRepository;

    public Estudante register(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

}
