package main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {

    private JFrame frame;
    private JPanel defaultpanel;
    private JPanel buttonpanel;
    private VolleyballClub club;
    private JScrollPane pane;
    private JTable table;
    private JLabel clubname;
    private JLabel year_of_foundation;
    static final int MAX = 20;
    static final int MIN = 0;
    static final int DEFAULT = 10;

    public Main() {
        frame = new JFrame("Volleyball Club Player Base");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        defaultpanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        buttonpanel = new JPanel(new GridLayout(1,8, 10, 10));
        buttonpanel.setPreferredSize(new Dimension(50,200));
        clubname = new JLabel();
        year_of_foundation = new JLabel();

        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        clubname.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        defaultpanel.add(clubname,c);

        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        year_of_foundation.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        defaultpanel.add(year_of_foundation,c);

        InitTable();

        c.gridwidth = 3;
        c.insets = new Insets(10,0, 10,0);
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        buttonpanel.setPreferredSize(new Dimension(100, 50));
        defaultpanel.add(buttonpanel,c);

        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        defaultpanel.add(pane,c);

        buttonpanel.add(AddPlayer());
        buttonpanel.add(Save_to_file_Button());
        buttonpanel.add(Load_from_file_Button());
        buttonpanel.add(Train_Player_Button());
        buttonpanel.add(Renew_Contract_Button());
        buttonpanel.add(Delete_Player_Button());
        buttonpanel.add(Sort_Players_Button());
        buttonpanel.add(Change_Shirt_Number_Button());

        buttonpanel.setBackground(new Color(127,181,181));
        defaultpanel.setBackground(new Color(127,181,181));

        Type_club();

        frame.getContentPane().add(defaultpanel);
        frame.pack();
        frame.setVisible(true);
        //frame.repaint();
    }

    public void InitTable()
    {
        club = new VolleyballClub();

        VolleyballPlayer p1 = new VolleyballPlayer("Mariusz","Wlazły","Poland","Opposite",36,2,2,18,17,18,17,15,15,16,15);
        VolleyballPlayer p2 = new VolleyballPlayer("Marcin","Janusz","Poland","Setter",26,3,6,14,16,16,16,12,10,16,15);
        VolleyballPlayer p3 = new VolleyballPlayer("Mateusz","Bieniek","Poland","Middle-blocker",27,1,4,18,19,18,16,12,12,15,14);
        VolleyballPlayer p4 = new VolleyballPlayer("Paweł","Zatorski","Poland","Libero",30,1,9,9,9,6,16,18,18,15,12);
        VolleyballPlayer p5 = new VolleyballPlayer("Milad","Ebadipour","Iran","Receiver",26,2,12,18,17,17,16,17,17,17,16);

        club.list_of_players.add(p1);
        club.list_of_players.add(p2);
        club.list_of_players.add(p3);
        club.list_of_players.add(p4);
        club.list_of_players.add(p5);

        String[] column_names = {"Name", "Surname", "Nationality", "Position", "Age", "Length of contract",
                "Number on shirt", "Attack", "Block", "Serve", "Speed", "Positioning", "Receiving", "Technique",
                "Decisions" };
        Object[][] content = new Object[20][15];
        table = new JTable(content, column_names);
        table.setEnabled(false);
        pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(1000, 330));

        for (VolleyballPlayer p : club.list_of_players)
        {
            fill_in_the_table(p, find_row());
        }
    }

    public void fill_in_the_table(VolleyballPlayer p, int row)
    {
        table.setValueAt(p.getName(),row,0);
        table.setValueAt(p.getSurname(),row,1);
        table.setValueAt(p.getNationality(),row,2);
        table.setValueAt(p.getPosition(),row,3);
        table.setValueAt(p.getAge(),row,4);
        table.setValueAt(p.getLength_of_contract(),row,5);
        table.setValueAt(p.getNumber_on_shirt(),row,6);
        table.setValueAt(p.getAttack(),row,7);
        table.setValueAt(p.getBlock(),row,8);
        table.setValueAt(p.getServe(),row,9);
        table.setValueAt(p.getSpeed(),row,10);
        table.setValueAt(p.getPositioning(),row,11);
        table.setValueAt(p.getReceiving(),row,12);
        table.setValueAt(p.getTechnique(),row,13);
        table.setValueAt(p.getDecisions(),row,14);
    }

    public int find_row()
    {
        for (int i = 0; i < table.getRowCount(); i++)
        {
            if (table.getValueAt(i, 0) == null) {
                return i;
            }
        }
        return 0;
    }

    public Object[] slider_creator()
    {
        Object[] ret = new Object[2];
        JSlider slider = new JSlider(JSlider.HORIZONTAL,MIN, MAX, DEFAULT);
        JLabel slider_text = new JLabel("Value: 10");
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                slider_text.setText("Value: " + String.valueOf((int)slider.getValue()));
            }
        });
        ret[0] = slider;
        ret[1] = slider_text;
        return ret;
    }

    public JButton AddPlayer()
    {
        JButton add_game_button = new JButton();
        add_game_button.setText("Add Player");
        add_game_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] positions = {"Opposite", "Libero", "Receiver", "Setter", "Middle-blocker"};
                JList list = new JList(positions);
                JTextField name = new JTextField();
                JTextField surname = new JTextField();
                JTextField nationality = new JTextField();
                JTextField age = new JTextField();
                JTextField length_of_contract = new JTextField();
                JTextField shirt_number = new JTextField();
                Object[] attack = slider_creator();
                Object[] block = slider_creator();
                Object[] serve = slider_creator();
                Object[] speed = slider_creator();
                Object[] positioning = slider_creator();
                Object[] receiving = slider_creator();
                Object[] technique = slider_creator();
                Object[] decisions = slider_creator();
                Object[] message = {"Name:", name, "Surname:", surname, "Nationality:", nationality,
                        "Position:", list, "Age [16-40]:", age, "Length of contract(in years) [1-5]:", length_of_contract,
                        "Shirt's Number [1-99]:", shirt_number, "Attack [0-20]:",
                        attack, "Block [0-20]:", block, "Serve [0-20]:", serve, "Speed [0-20]:", speed,
                        "Positioning [0-20]:", positioning, "Receiving [0-20]:", receiving,
                        "Technique [0-20]:", technique, "Decisions [0-20]:", decisions};
                int option = JOptionPane.showConfirmDialog(null,message,"Input Data",JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION)
                {
                    String namee, surnamee, nationalityy, position;
                    int att, blo, ser, spee, pos, rec, tec, dec;
                    Integer agee, length_of_contractt, shirt_numberr;
                    namee =  name.getText();
                    surnamee = surname.getText();
                    for (VolleyballPlayer p : club.list_of_players)
                    {
                        if (surnamee.equals(p.getSurname()) && namee.equals(p.getName()))
                        {
                            JOptionPane.showMessageDialog(null,"There is already that player in the club");
                            return;
                        }
                    }
                    nationalityy =  nationality.getText();
                    position = (String) list.getSelectedValue();
                    if (position == null)
                    {
                        JOptionPane.showMessageDialog(null,"You must choose position");
                        return;
                    }
                    try{
                        agee =  parseInt(age.getText());
                        length_of_contractt = parseInt(length_of_contract.getText());
                        shirt_numberr =  parseInt(shirt_number.getText());
                        if (shirt_numberr > 99 || shirt_numberr < 1 || length_of_contractt > 5 || length_of_contractt < 1 || agee < 16 || agee > 40)
                        {
                            JOptionPane.showMessageDialog(null,"Invalid type of input data");
                            return;
                        }
                    }
                    catch (NumberFormatException e1)
                    {
                        JOptionPane.showMessageDialog(null,"Invalid type of input data");
                        return;
                    }


                    JSlider s1 = (JSlider) attack[0];
                    att = (int)s1.getValue();
                    JSlider s2 = (JSlider) block[0];
                    blo = (int)s2.getValue();
                    JSlider s3 = (JSlider) serve[0];
                    ser = (int)s3.getValue();
                    JSlider s4 = (JSlider) speed[0];
                    spee = (int)s4.getValue();
                    JSlider s5 = (JSlider) positioning[0];
                    pos = (int)s5.getValue();
                    JSlider s6 = (JSlider) receiving[0];
                    rec = (int)s6.getValue();
                    JSlider s7 = (JSlider) technique[0];
                    tec = (int)s7.getValue();
                    JSlider s8 = (JSlider) decisions[0];
                    dec = (int)s8.getValue();
                    if(namee.length() == 0 || surnamee.length() == 0 || nationalityy.length() == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Missing data","Missing data",JOptionPane.OK_CANCEL_OPTION);
                        return;
                    }
                    VolleyballPlayer p = new VolleyballPlayer(namee, surnamee, nationalityy, position, agee, length_of_contractt, shirt_numberr, att, blo, ser, spee,pos,rec,tec,dec);
                    club.list_of_players.add(p);
                    fill_in_the_table(p,find_row());
                }
            }
        });
        return add_game_button;
    }

    public JButton Save_to_file_Button ()
    {
        JButton save_to_file_button = new JButton();
        save_to_file_button.setText("Save to file");
        save_to_file_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                club.saveTofile("volleyball.ser");
            }
        });
        return save_to_file_button;
    }

    public JButton Load_from_file_Button ()
    {
        JButton load_from_file_button = new JButton();
        load_from_file_button.setText("Load data from file");
        load_from_file_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String file_name;
                List<String> files_names = new ArrayList<String>();
                File folder = new File(".");
                File[] files = folder.listFiles();
                if (files == null) {
                    JOptionPane.showMessageDialog(null, "There is not any valid file!");
                } else {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".ser")) {
                            files_names.add(file.getName());
                        }
                    }
                    JList list = new JList(files_names.toArray());
                    Object[] input =
                            {
                                    "Choose file: ", list
                            };
                    int option = JOptionPane.showConfirmDialog(null, input, "Input", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        file_name = (String) list.getSelectedValue();
                        if(file_name == null) {
                            JOptionPane.showMessageDialog(null, "Choose a file.");
                        } else {
                            club.readFromfile(file_name);
                            //clubname.setText("Name of the club: " + club.getName_of_club());
                            String tmp = Integer.toString(club.getYear_of_foundation());
                            //year_of_foundation.setText("Year of foundation: " + tmp);
                            ClearTable();
                            for (VolleyballPlayer p : club.list_of_players) {
                                fill_in_the_table(p, find_row());
                            }
                        }
                    }
                }
            }
        });
        return load_from_file_button;
    }

    public JButton Train_Player_Button ()
    {
        JButton train_player_button = new JButton("Train Player");
        train_player_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] surnames = new String[25];
                int j = 0;
                for (int i = 0; i < 20; i++)
                {
                    if (table.getValueAt(i, 1) != null)
                    {
                        surnames[j] = (table.getValueAt(i,1).toString());
                        j++;
                    }
                }
                if(club.list_of_players.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"There is no player in club");
                    return;
                }
                String[] abilities = {"Attack", "Block", "Serve", "Speed", "Positioning", "Receiving", "Technique", "Decisions"};
                JList list = new JList(surnames);
                JList list2 = new JList(abilities);
                Object[] message = {"Choose Volleyball Player:", list, "Choose trained ability:", list2};
                int option = JOptionPane.showConfirmDialog(null,message,"Train Player",JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String surname, ability;
                    surname = (String) list.getSelectedValue();
                    ability = (String) list2.getSelectedValue();
                    if (surname == null || ability == null)
                    {
                        JOptionPane.showMessageDialog(null,"You must choose player and his ability to train");
                        return;
                    }
                    for (int i = 0; i < 20; i++)
                    {
                        if (table.getValueAt(i, 1) == surname)
                        {
                            if (ability.equals("Attack")) {
                                int tmp = (int)table.getValueAt(i, 7);
                                if (tmp == 20)
                                {
                                    JOptionPane.showMessageDialog(null,"Player has already that ability on max level", "Train Player", JOptionPane.OK_CANCEL_OPTION);
                                }
                                else
                                {
                                    tmp++;
                                    club.list_of_players.get(i).setAttack(tmp);
                                    table.setValueAt(tmp, i, 7);
                                    JOptionPane.showMessageDialog(null, "In the result of training player increased level of the ability");
                                }
                            }
                            if (ability.equals("Block")) {
                                int tmp = (int)table.getValueAt(i, 8);
                                if (tmp == 20)
                                {
                                    JOptionPane.showMessageDialog(null,"Player has already that ability on max level", "Train Player", JOptionPane.OK_CANCEL_OPTION);
                                }
                                else
                                {
                                    tmp++;
                                    club.list_of_players.get(i).setBlock(tmp);
                                    table.setValueAt(tmp, i, 8);
                                    JOptionPane.showMessageDialog(null, "In the result of training player increased level of the ability");
                                }
                            }
                            if (ability.equals("Serve")) {
                                int tmp = (int)table.getValueAt(i, 9);
                                if (tmp == 20)
                                {
                                    JOptionPane.showMessageDialog(null,"Player has already that ability on max level", "Train Player", JOptionPane.OK_CANCEL_OPTION);
                                }
                                else
                                {
                                    tmp++;
                                    club.list_of_players.get(i).setServe(tmp);
                                    table.setValueAt(tmp, i, 9);
                                    JOptionPane.showMessageDialog(null, "In the result of training player increased level of the ability");
                                }
                            }
                            if (ability.equals("Speed")) {
                                int tmp = (int)table.getValueAt(i, 10);
                                if (tmp == 20)
                                {
                                    JOptionPane.showMessageDialog(null,"Player has already that ability on max level", "Train Player", JOptionPane.OK_CANCEL_OPTION);
                                }
                                else
                                {
                                    tmp++;
                                    club.list_of_players.get(i).setSpeed(tmp);
                                    table.setValueAt(tmp, i, 10);
                                    JOptionPane.showMessageDialog(null, "In the result of training player increased level of the ability");
                                }
                            }
                            if (ability.equals("Positioning")) {
                                int tmp = (int)table.getValueAt(i, 11);
                                if (tmp == 20)
                                {
                                    JOptionPane.showMessageDialog(null,"Player has already that ability on max level", "Train Player", JOptionPane.OK_CANCEL_OPTION);
                                }
                                else
                                {
                                    tmp++;
                                    club.list_of_players.get(i).setPositioning(tmp);
                                    table.setValueAt(tmp, i, 11);
                                    JOptionPane.showMessageDialog(null, "In the result of training player increased level of the ability");
                                }
                            }
                            if (ability.equals("Receiving")) {
                                int tmp = (int)table.getValueAt(i, 12);
                                if (tmp == 20)
                                {
                                    JOptionPane.showMessageDialog(null,"Player has already that ability on max level", "Train Player", JOptionPane.OK_CANCEL_OPTION);
                                }
                                else
                                {
                                    tmp++;
                                    club.list_of_players.get(i).setReceiving(tmp);
                                    table.setValueAt(tmp, i, 12);
                                    JOptionPane.showMessageDialog(null, "In the result of training player increased level of the ability");
                                }
                            }
                            if (ability.equals("Technique")) {
                                int tmp = (int)table.getValueAt(i, 13);
                                if (tmp == 20)
                                {
                                    JOptionPane.showMessageDialog(null,"Player has already that ability on max level", "Train Player", JOptionPane.OK_CANCEL_OPTION);
                                }
                                else
                                {
                                    tmp++;
                                    club.list_of_players.get(i).setTechnique(tmp);
                                    table.setValueAt(tmp, i, 13);
                                    JOptionPane.showMessageDialog(null, "In the result of training player increased level of the ability");
                                }
                            }
                            if (ability.equals("Decisions")) {
                                int tmp = (int)table.getValueAt(i, 14);
                                if (tmp == 20)
                                {
                                    JOptionPane.showMessageDialog(null,"Player has already that ability on max level", "Train Player", JOptionPane.OK_CANCEL_OPTION);
                                }
                                else
                                {
                                    tmp++;
                                    club.list_of_players.get(i).setDecisions(tmp);
                                    table.setValueAt(tmp, i, 14);
                                    JOptionPane.showMessageDialog(null, "In the result of training player increased level of the ability");
                                }
                            }
                        }
                    }
                }

            }
        });
        return train_player_button;
    }

    public JButton Renew_Contract_Button ()
    {
        JButton renew_contract_button = new JButton("Re-new Contract");
        renew_contract_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] surnames = new String[25];
                int j = 0;
                for (int i = 0; i < 20; i++)
                {
                    if (table.getValueAt(i, 1) != null)
                    {
                        surnames[j] = (table.getValueAt(i,1).toString());
                        j++;
                    }
                }
                if(club.list_of_players.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"There is no player in club");
                    return;
                }
                JList list = new JList(surnames);
                Object[] message = {"Choose player:", list};
                int option = JOptionPane.showConfirmDialog(null,message,"Re-new contract",JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String surname;
                    surname = (String) list.getSelectedValue();
                    if (surname == null)
                    {
                        JOptionPane.showMessageDialog(null,"You must choose player and his ability to train");
                        return;
                    }
                    for (int i = 0; i < 20; i++) {
                        if (table.getValueAt(i, 1) == surname) {
                            int tmp = (int) table.getValueAt(i, 5);
                            if (tmp == 3)
                            {
                                JOptionPane.showMessageDialog(null,"The player didn't agree to extend the contract due to current long contract.", "Renewing contract",JOptionPane.OK_CANCEL_OPTION);
                            }
                            else{
                                tmp++;
                                club.list_of_players.get(i).setLength_of_contract(tmp);
                                table.setValueAt(tmp, i, 5);
                                JOptionPane.showMessageDialog(null,"The player agreed to extend the contract with club","Renewing contract",JOptionPane.OK_CANCEL_OPTION);
                            }
                        }
                    }
                }
            }
        });
        return renew_contract_button;
    }

    public JButton Delete_Player_Button ()
    {
        JButton delete_player_button = new JButton("Delete player");
        delete_player_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] surnames = new String[25];
                int j = 0;
                for (int i = 0; i < 20; i++)
                {
                    if (table.getValueAt(i, 1) != null)
                    {
                        surnames[j] = (table.getValueAt(i,1).toString());
                        j++;
                    }
                }
                if(club.list_of_players.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"There is no player to delete in club");
                    return;
                }

                JList list = new JList(surnames);
                Object[] message = {"Choose player:", list};
                int option = JOptionPane.showConfirmDialog(null,message,"Delete player",JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String surname;
                    surname = (String) list.getSelectedValue();
                    if (surname == null)
                    {
                        JOptionPane.showMessageDialog(null,"You must choose player");
                        return;
                    }
                    for (int i = 0; i < 20; i++) {
                        if (table.getValueAt(i, 1) == surname) {
                            club.list_of_players.remove(i);
                            for (int k = 0; k < 15; k++)
                            {
                                table.setValueAt(null, i, k);
                            }
                        }
                    }
                }
                ClearTable();
                for (VolleyballPlayer p : club.list_of_players)
                {
                    fill_in_the_table(p, find_row());
                }
        }});
        return delete_player_button;
    }

    public JButton Sort_Players_Button ()
    {
        JButton sort_players_button = new JButton("Sort players");
        sort_players_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(club.list_of_players.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"There is no player in club");
                    return;
                }
                String[] parameters = {"Name", "Surname", "Nationality",
                        "Position", "Age", "Length of contract", "Shirt's Number", "Attack", "Block",
                        "Serve", "Speed", "Positioning", "Receiving", "Technique", "Decisions", };
                JList list = new JList(parameters);
                JCheckBox reversed = new JCheckBox();
                Object[] message = {"Choose parameter:", list, "Reversed:", reversed};
                int option = JOptionPane.showConfirmDialog(null, message,"Sorting players",JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION)
                {
                    String select;
                    select = (String) list.getSelectedValue();
                    if(select == null) {
                        JOptionPane.showMessageDialog(null, "Choose one parameter.");
                    } else {
                        ClearTable();
                        if (select.equals("Name")) {
                            club.sortByName(reversed.isSelected());
                        }
                        if (select.equals("Surname")) {
                            club.sortBySurname(reversed.isSelected());
                        }
                        if (select.equals("Nationality")) {
                            club.sortByNationality(reversed.isSelected());
                        }
                        if (select.equals("Position")) {
                            club.sortByPosition(reversed.isSelected());
                        }
                        if (select.equals("Length of contract")) {
                            club.sortByLength_of_contract(reversed.isSelected());
                        }
                        if (select.equals("Age")) {
                            club.sortByAge(reversed.isSelected());
                        }
                        if (select.equals("Shirt's Number")) {
                            club.sortByNumber_on_shirt(reversed.isSelected());
                        }
                        if (select.equals("Attack")) {
                            club.sortByAttack(reversed.isSelected());
                        }
                        if (select.equals("Block")) {
                            club.sortByBlock(reversed.isSelected());
                        }
                        if (select.equals("Serve")) {
                            club.sortByServe(reversed.isSelected());
                        }
                        if (select.equals("Speed")) {
                            club.sortBySpeed(reversed.isSelected());
                        }
                        if (select.equals("Positioning")) {
                            club.sortByPositioning(reversed.isSelected());
                        }
                        if (select.equals("Receiving")) {
                            club.sortByReceiving(reversed.isSelected());
                        }
                        if (select.equals("Technique")) {
                            club.sortByTechnique(reversed.isSelected());
                        }
                        if (select.equals("Decisions")) {
                            club.sortByDecisions(reversed.isSelected());
                        }

                        for (VolleyballPlayer p : club.list_of_players) {
                            fill_in_the_table(p, find_row());
                        }
                    }
                }
            }
        });
        return sort_players_button;
    }

    public JButton Change_Shirt_Number_Button ()
    {
        JButton change_shirt_number_button = new JButton("Change player's shirt's number");
        change_shirt_number_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] surnames = new String[25];
                int j = 0;
                for (int i = 0; i < 20; i++)
                {
                    if (table.getValueAt(i, 1) != null)
                    {
                        surnames[j] = (table.getValueAt(i,1).toString());
                        j++;
                    }
                }
                if(club.list_of_players.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"There is no player in club");
                    return;
                }
                JList list = new JList(surnames);
                JSlider slider = new JSlider(JSlider.HORIZONTAL,1, 99, 10);
                JLabel slider_text = new JLabel("Number: 10");
                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        slider_text.setText("Number: " + String.valueOf((int)slider.getValue()));
                    }
                });
                Object[] new_number = new Object[2];
                new_number[0] = slider;
                new_number[1] = slider_text;
                Object[] message = {"Choose player:", list, "Enter new number:", new_number};
                int option = JOptionPane.showConfirmDialog(null,message,"Changing shirt's number", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION)
                {
                    String surname;
                    int new_nr;
                    surname = (String) list.getSelectedValue();
                    if (surname == null)
                    {
                        JOptionPane.showMessageDialog(null,"You must choose player");
                        return;
                    }
                    JSlider s1 = (JSlider) new_number[0];
                    new_nr = (int)s1.getValue();
                    for (VolleyballPlayer p : club.list_of_players)
                    {
                        if(new_nr == p.getNumber_on_shirt())
                        {
                            JOptionPane.showMessageDialog(null,"That number is already used in club");
                            return;
                        }
                    }

                    for (int i = 0; i < 20; i++)
                    {
                        if (table.getValueAt(i, 1) == surname)
                        {
                            club.list_of_players.get(i).setNumber_on_shirt(new_nr);
                            ClearTable();
                            for (VolleyballPlayer p : club.list_of_players)
                            {
                                fill_in_the_table(p, find_row());
                            }
                        }
                }
            }
        }
    });
    return change_shirt_number_button;
    }

    public void ClearTable()
    {
        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                table.setValueAt(null, i, j);
            }
        }
    }

    public void Type_club()
    {
        JTextField club_name = new JTextField();
        JTextField foundation_year = new JTextField();
        Object[] message = {"Input name of the club", club_name, "Input year of foundation:", foundation_year};
        int option = JOptionPane.showConfirmDialog(null,message,"Input Data",JOptionPane.OK_CANCEL_OPTION);
        String name, year;
        if (option == JOptionPane.OK_OPTION) {
            name = club_name.getText();
            year = foundation_year.getText();
            if (name.length() == 0 || year.length() == 0)
            {
                JOptionPane.showMessageDialog(null,"Incorrect input data","Incorrect data", JOptionPane.OK_CANCEL_OPTION);
                System.exit(0);
            }
            club.setName_of_club(name);
            try {
                club.setYear_of_foundation(parseInt(year));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Given data is incorrect", "Wrong data", JOptionPane.OK_CANCEL_OPTION);
                System.exit(0);
            }
            clubname.setText("Name of the club: " + name);
            year_of_foundation.setText("Year of foundation: " + year);
        }else
        {
            JOptionPane.showMessageDialog(null,"Incorrect input data","Incorrect data", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
    }

    public static void main(String[] args){
        new Main();
    }
}
