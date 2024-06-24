package aplicacao;

import dados.ListaRobos;
import dados.Robo;

public class ACMERobots {
    private ListaRobos listaRobos;

    public ACMERobots(){
        listaRobos = new ListaRobos();
    }

    public void cadastrarRobo(Robo robo) throws Exception {
        if(listaRobos.consultaRobo(robo.getId()) == null){
            listaRobos.cadastraRobo(robo);
        } else {
            throw new Exception("Erro: ID já cadastrado.");
        }
    }

    public void robosCadastrados() throws Exception{
        if(!listaRobos.getRobos().isEmpty()){
            listaRobos.toString();
        } else{
            throw new Exception("Nenhum evento cadastrado.");
        }
    }
}