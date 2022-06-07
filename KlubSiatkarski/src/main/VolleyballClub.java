package main;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VolleyballClub {
    int year_of_foundation;
    String name_of_club;
    public List<VolleyballPlayer> list_of_players = new ArrayList<>();

    public VolleyballClub() {};

    public VolleyballClub(String name, int year_of_foundation)
    {
        this.name_of_club = name;
        this.year_of_foundation = year_of_foundation;
    }

    public List<VolleyballPlayer> getList_of_players() {
        return list_of_players;
    }

    public void setList_of_players(ArrayList<VolleyballPlayer> list_of_players) {
        this.list_of_players = list_of_players;
    }

    public int getYear_of_foundation() {
        return year_of_foundation;
    }

    public String getName_of_club() {
        return name_of_club;
    }

    public void setName_of_club(String name_of_club) {
        this.name_of_club = name_of_club;
    }

    public void setYear_of_foundation(int year_of_foundation) {
        this.year_of_foundation = year_of_foundation;
    }

    public void saveTofile(String filename)
    {
        try{
            FileOutputStream file_out = new FileOutputStream(filename);
            ObjectOutputStream object_out1 = new ObjectOutputStream(file_out);
            ObjectOutputStream object_out2 = new ObjectOutputStream(file_out);
            ObjectOutputStream object_out = new ObjectOutputStream(file_out);
            object_out1.writeObject(name_of_club);
            object_out2.writeObject(year_of_foundation);
            object_out.writeObject(list_of_players);
            object_out.flush();
            object_out.close();
            System.out.println("Games were successfully saved");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromfile (String filename)
    {
        try {
            File folder = new File(".");
            File[] files = folder.listFiles();
            if(files == null) {
                System.out.println("There is not any valid file!");
            } else {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".ser")) {
                        System.out.println("File: " + file.getName());
                    }
                }
                while(true) {
                    try {
                        FileInputStream file_in = new FileInputStream(filename);
                        ObjectInputStream object_in1 = new ObjectInputStream(file_in);
                        ObjectInputStream object_in2 = new ObjectInputStream(file_in);
                        ObjectInputStream object_in = new ObjectInputStream(file_in);
                        name_of_club = (String) object_in1.readObject();
                        year_of_foundation = (int) object_in2.readObject();
                        list_of_players = (ArrayList<VolleyballPlayer>) object_in.readObject();
                        break;
                    } catch (FileNotFoundException e) {
                        System.out.println("File does not exist!");
                    }
                }
            }
        } catch(ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    Comparator<VolleyballPlayer> compareByName = Comparator.comparing(VolleyballPlayer::getName);

    Comparator<VolleyballPlayer> compareBySurname = Comparator.comparing(VolleyballPlayer::getSurname);

    Comparator<VolleyballPlayer> compareByNationality = Comparator.comparing(VolleyballPlayer::getNationality);

    Comparator<VolleyballPlayer> compareByPosition = Comparator.comparing(VolleyballPlayer::getPosition);

    Comparator<VolleyballPlayer> compareByAge = (p1, p2) -> Integer.compare(p2.getAge(), p1.getAge());

    Comparator<VolleyballPlayer> compareByLength_of_contract = (p1, p2) -> Integer.compare(p2.getLength_of_contract(), p1.getLength_of_contract());

    Comparator<VolleyballPlayer> compareByNumber_on_shirt = (p1, p2) -> Integer.compare(p2.getNumber_on_shirt(), p1.getNumber_on_shirt());

    Comparator<VolleyballPlayer> compareByAttack = (p1, p2) -> Integer.compare(p2.getAttack(), p1.getAttack());

    Comparator<VolleyballPlayer> compareByBlock = (p1, p2) -> Integer.compare(p2.getBlock(), p1.getBlock());

    Comparator<VolleyballPlayer> compareByServe = (p1, p2) -> Integer.compare(p2.getServe(), p1.getServe());

    Comparator<VolleyballPlayer> compareBySpeed = (p1, p2) -> Integer.compare(p2.getSpeed(), p1.getSpeed());

    Comparator<VolleyballPlayer> compareByPositioning = (p1, p2) -> Integer.compare(p2.getPositioning(), p1.getPositioning());

    Comparator<VolleyballPlayer> compareByReceiving = (p1, p2) -> Integer.compare(p2.getReceiving(), p1.getReceiving());

    Comparator<VolleyballPlayer> compareByTechnique = (p1, p2) -> Integer.compare(p2.getTechnique(), p1.getTechnique());

    Comparator<VolleyballPlayer> compareByDecisions = (p1, p2) -> Integer.compare(p2.getDecisions(), p1.getDecisions());

    public void sortByName(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByName.reversed());
        } else {
            list_of_players.sort(compareByName);
        }

    }

    public void sortBySurname(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareBySurname.reversed());
        } else {
            list_of_players.sort(compareBySurname);
        }
    }

    public void sortByNationality(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByNationality.reversed());
        } else {
            list_of_players.sort(compareByNationality);
        }
    }

    public void sortByPosition(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByPosition.reversed());
        } else {
            list_of_players.sort(compareByPosition);
        }
    }

    public void sortByAge(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByAge.reversed());
        } else {
            list_of_players.sort(compareByAge);
        }
    }

    public void sortByLength_of_contract(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByLength_of_contract.reversed());
        } else {
            list_of_players.sort(compareByLength_of_contract);
        }
    }

    public void sortByNumber_on_shirt(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByNumber_on_shirt.reversed());
        } else {
            list_of_players.sort(compareByNumber_on_shirt);
        }
    }

    public void sortByAttack(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByAttack.reversed());
        } else {
            list_of_players.sort(compareByAttack);
        }
    }

    public void sortByBlock(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByBlock.reversed());
        } else {
            list_of_players.sort(compareByBlock);
        }
    }

    public void sortByServe(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByServe.reversed());
        } else {
            list_of_players.sort(compareByServe);
        }
    }

    public void sortBySpeed(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareBySpeed.reversed());
        } else {
            list_of_players.sort(compareBySpeed);
        }
    }

    public void sortByPositioning(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByPositioning.reversed());
        } else {
            list_of_players.sort(compareByPositioning);
        }
    }

    public void sortByReceiving(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByReceiving.reversed());
        } else {
            list_of_players.sort(compareByReceiving);
        }
    }

    public void sortByTechnique(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByTechnique.reversed());
        } else {
            list_of_players.sort(compareByTechnique);
        }
    }

    public void sortByDecisions(boolean reversed) {
        if(reversed) {
            list_of_players.sort(compareByDecisions.reversed());
        } else {
            list_of_players.sort(compareByDecisions);
        }
    }
}
