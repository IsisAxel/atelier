package mg.atelier.service;

import mg.atelier.model.Reparation;
import mg.atelier.model.ReparationDetail;
import mg.atelier.model.Return;
import mg.atelier.repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;

import java.util.List;

@Service
public class ReturnService
{
    @Autowired
    private ReturnRepository returnRepository;

    public Return createReturn(Return returnn) {
        return returnRepository.save(returnn);
    }

    public List<Return> getAllReturn() {
        return returnRepository.findAll();
    }

    public List<Return> getAllReturnByComputerUsageAndReparationType(List<ReparationDetail> reparationDetails , int idComputerUsage) {
        List<Return> returns = returnRepository.findAll();
        for (ReparationDetail rep : reparationDetails) 
        {
            Reparation tmp=rep.getReparation();
            if(!(rep.getReparation().getComputer().getComputerUsage().getIdComputerUsage()==idComputerUsage || idComputerUsage==0)){
                for (Return return1 : returns) {
                    if (return1.getReparation().getIdReparation() == tmp.getIdReparation()) {
                        returns.remove(return1);
                        break;
                    }
                }
            }    
        }
        return returns;
    }

    public Return getReturnById(int id) throws EntityNotFoundException{
        return returnRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Retour"));
    }

    public Return getReturnByReparation(Reparation reparation) {
        return returnRepository.findByReparation(reparation);
    }
}