package ui;
import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Objects;

public class JanelaPrincipal extends JFrame {
    private JPanel painel;
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
    private JTextArea AreaDados;
    private JComboBox tipoRobo;
    private ListaRobos listaRobos = new ListaRobos();

    public JanelaPrincipal() {
        super();
        selecionaTipo();

        JFrame frame = new JFrame();
        frame.setContentPane(getCadastro());
        frame.setSize(600,400);
        frame.setTitle("ACMERobots");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });
        botaoDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dadosRobos();
            }
        });
        botaoFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarRobo();
            }
        });
        tipoRobo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Nivel.setEnabled(false);
                Setor.setEnabled(false);
                Area.setEnabled(false);
                Uso.setEnabled(false);

                String tipoSelecionado = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
                switch (tipoSelecionado) {
                    case "Domestico":
                        Nivel.setEnabled(true);
                        break;
                    case "Industrial":
                        Setor.setEnabled(true);
                        break;
                    case "Agricola":
                        Area.setEnabled(true);
                        Uso.setEnabled(true);
                        break;
                }
            }
        });

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

    private void dadosRobos(){
        Collections.sort(listaRobos.getRobos());
        for (Robo r : listaRobos.getRobos()) {
            AreaDados.append("ID: " + r.getId() + "\n");
            AreaDados.append("Modelo: " + r.getModelo() + "\n");
            AreaDados.append("Valor Diário: " + r.getValorDiario() + "\n");
            if (r instanceof Domestico) {
                AreaDados.append("Nível: " + ((Domestico) r).getNivel() + "\n");
            } else if (r instanceof Industrial) {
                AreaDados.append("Setor: " + ((Industrial) r).getSetor() + "\n");
            } else if (r instanceof Agricola) {
                AreaDados.append("Área: " + ((Agricola) r).getArea() + "\n");
                AreaDados.append("Uso: " + ((Agricola) r).getUso() + "\n");
            }
            AreaDados.append("\n");
        }
    }


    private void cadastrarRobo(){
        String tipo = (String) tipoRobo.getSelectedItem();
        if (tipo == null || "Selecione o tipo do robô:".equals(tipo)) {
            AreaDados.append("ERRO: Selecione um tipo de robô.\n");
            return;
        }

        try {
            int id = Integer.parseInt(Id.getText());
            String modelo = Modelo.getText();
            double valorDiario = Double.parseDouble(ValorDiario.getText());

            if (modelo == null || modelo.trim().isEmpty()) {
                AreaDados.append("ERRO: Modelo não pode estar vazio.\n");
                return;
            }

            switch (tipo) {
                case "Domestico" -> {
                    int nivel = Integer.parseInt(Nivel.getText());
                    Domestico roboDomestico = new Domestico(id, modelo, valorDiario, nivel);
                    if (listaRobos.consultaRobo(id)) {
                        AreaDados.append("ERRO: ID já cadastrado.\n");
                    } else {
                        try {
                            listaRobos.cadastraRobo(roboDomestico);
                            AreaDados.append("Robô cadastado!\n");
                        } catch (Exception ex) {
                            AreaDados.append("ERRO: " + ex.getMessage() + "\n");
                        }
                    }
                }
                case "Industrial" -> {
                    String setor = Setor.getText();
                    if (setor == null || setor.trim().isEmpty()) {
                        AreaDados.append("ERRO: Setor não pode estar vazio.\n");
                        return;
                    }
                    Industrial roboIndustrial = new Industrial(id, modelo, valorDiario, setor);

                    if (listaRobos.consultaRobo(id)) {
                        AreaDados.append("ERRO: ID já cadastrado.\n");
                    } else {
                        try {
                            listaRobos.cadastraRobo(roboIndustrial);
                            AreaDados.append("Robô cadastado!\n");
                        } catch (Exception ex) {
                            AreaDados.append("ERRO: " + ex.getMessage() + "\n");
                        }
                    }
                }
                case "Agricola" -> {
                    double area = Double.parseDouble(Area.getText());
                    String uso = Uso.getText();
                    if (uso == null || uso.trim().isEmpty()) {
                        AreaDados.append("ERRO: Uso não pode estar vazio.\n");
                        return;
                    }
                    Agricola roboAgricola = new Agricola(id, modelo, valorDiario, area, uso);
                    if (listaRobos.consultaRobo(id)) {
                        AreaDados.append("ERRO: ID já cadastrado.\n");
                    } else {
                        try {
                            listaRobos.cadastraRobo(roboAgricola);
                            AreaDados.append("Robô cadastado!\n");
                        } catch (Exception ex) {
                            AreaDados.append("ERRO: " + ex.getMessage() + "\n");
                        }
                    }
                }
            }
            limpar();
        } catch (NumberFormatException e) {
            AreaDados.append("ERRO: Verifique os dados inseridos.\n");
        } catch (NullPointerException e) {
            AreaDados.append("ERRO: Verifique se todos os campos estão preenchidos.\n");
        } catch (Exception e) {
            AreaDados.append("ERRO: " + e.getMessage() + "\n"); //essa exceção é lançada
        }
    }

    private void selecionaTipo(){
        DefaultComboBoxModel<String> tipoEvento = new DefaultComboBoxModel<>();
        tipoEvento.addElement("Selecione o tipo do robô:");
        tipoEvento.addElement("Agricola");
        tipoEvento.addElement("Domestico");
        tipoEvento.addElement("Industrial");
        tipoRobo.setModel(tipoEvento);
    }

    public JPanel getCadastro() {
        return painel;
    }
}
