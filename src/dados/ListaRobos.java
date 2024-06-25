package dados;

import java.util.ArrayList;

public class ListaRobos {
    private ArrayList<Robo> robos;

    public ListaRobos(){
        robos = new ArrayList<>();
    }

    public boolean cadastraRobo(Robo robo){
        if(!robos.isEmpty()){
            for(Robo r : robos){
                if(r.getId() == robo.getId()){
                    return false;
                }
            }
        }
        robos.add(robo);
        return true;
    }

    public boolean consultaRobo(int id){
        if(!robos.isEmpty()){
            for(Robo r : robos){
                if(r.getId() == id){
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Robo> getRobos(){
        return robos;
    }

    @Override
    public String toString() {
        StringBuilder rob = new StringBuilder(" ");

        for(Robo r : robos){
            rob.append("\n").append(r).append("\n");
        }
        return rob.toString();
    }
}
