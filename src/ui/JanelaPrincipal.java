package ui;

import aplicacao.ACMERobots;
import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class JanelaPrincipal extends JFrame implements ActionListener {
    private JPanel painel;
    private JComboBox TipoRobo;
    private JTextField Id;
    private JTextField Modelo;
    private JTextField ValorDiario;
    private JTextField Nivel;
    private JTextField Setor;
    private JTextField Area;
    private JTextField Uso;
    private JButton botaoFechar;
    private JButton botaoCadastrar;
    private JButton botaoDados;
    private JButton botaoLimpar;
    private ACMERobots acmeRobots = new ACMERobots();
    private ArrayList<Robo> robos = new ArrayList<>();

    public JanelaPrincipal() {
        super();
        selecionaTipo();

        botaoCadastrar.addActionListener(this);
        botaoLimpar.addActionListener(this);
        botaoDados.addActionListener(this);
        botaoFechar.addActionListener(this);

        JFrame frame = new JFrame();
        frame.setContentPane(getCadastro());
        frame.setSize(600,400);
        frame.setTitle("ACMERobots");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JPanel getCadastro() {
        return painel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoCadastrar) {
            cadastrarEvento();
        } else if(e.getSource() == botaoLimpar){
            limpar();
        } else if(e.getSource() == botaoDados){
            Collections.sort(robos);
            for(Robo r : robos){
                r.toString();
            }
        } else if(e.getSource() == botaoFechar){
            System.exit(0);
        } else if (e.getSource() == TipoRobo){
            tipoComboBox((ActionEvent) e.getSource());
        }

    }

    private void limpar(){
        Id.setText("");
        Modelo.setText("");
        ValorDiario.setText("");
        Nivel.setText("");
        Setor.setText("");
        Area.setText("");
        Uso.setText("");
    }


    private void cadastrarEvento(){
        try{
            int id = Integer.parseInt(Id.getText());
            String modelo = Modelo.getText();
            double valorDiario = Double.parseDouble(ValorDiario.getText());
            String tipo = TipoRobo.getSelectedItem().toString();
            switch (tipo) {
                case "Tipo de Evento" -> {
                    JOptionPane.showMessageDialog(null, "ERRO: Selecione um tipo de evento.");
                }
                case "Domestico" -> {

                    int nivel = Integer.parseInt(Nivel.getText());
                    Domestico roboDomestico = new Domestico(id, modelo, valorDiario, nivel);
                    try {
                        acmeRobots.cadastrarRobo(roboDomestico);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                }
                case "Industrial" -> {

                    String setor = Setor.getText();
                    Industrial roboIndustrial = new Industrial(id, modelo, valorDiario, setor);
                    try {
                        acmeRobots.cadastrarRobo(roboIndustrial);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case "Agricola" -> {

                    double area = Double.parseDouble(Area.getText());
                    String uso = Uso.getText();
                    Agricola roboAgricola = new Agricola(id, modelo, valorDiario, area, uso);
                    try {
                        acmeRobots.cadastrarRobo(roboAgricola);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void selecionaTipo() {
        DefaultComboBoxModel<String> tipoEvento = new DefaultComboBoxModel<>();
        tipoEvento.addElement("Tipo de Robo");
        tipoEvento.addElement("Agricola");
        tipoEvento.addElement("Domestico");
        tipoEvento.addElement("Industrial");
        TipoRobo.setModel(tipoEvento);
    }

    private void tipoComboBox(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();

        Area.setEnabled(false);
        Uso.setEnabled(false);
        Nivel.setEnabled(false);
        Setor.setEnabled(false);

        String tipo = comboBox.getSelectedItem().toString();

        switch (tipo) {
            case "Agricola" -> {
                Area.setEnabled(true);
                Uso.setEnabled(true);
            }
            case "Domestico" -> Nivel.setEnabled(true);
            case "Industrial" -> Setor.setEnabled(true);
        }

    }
}
