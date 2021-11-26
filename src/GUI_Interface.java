import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

class MainFrame extends JFrame implements ChampionshipManager {
    ArrayList<Formula1Driver> driversList = new ArrayList<>();
    ArrayList<Race> racesList = new ArrayList<>();

    MainFrame(String title) {
        super(title);
        readFromFile();
//----------------------------------------------------Driver Panel----------------------------------------------------
        JPanel driverPanel = new JPanel(new BorderLayout());

        //Table
        String[] driverColumns = {"Team", "Driver", "Location", "Points", "Races", "1st places", "2nd places",
                "3rd places"};
        JTable driverTable = new JTable(loadDataDriverTable(driversList), driverColumns);
        refreshDriverTable(driverTable, driverColumns);
        driverTable.setDefaultEditor(Object.class, null);

        JTableHeader driverTblHeader = driverTable.getTableHeader();
        driverTblHeader.setBackground(Color.gray);
        driverTblHeader.setForeground(Color.white);
        driverTblHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        JScrollPane driverScrollPane = new JScrollPane(driverTable);
        driverTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        //Buttons
        JButton descendingBtn = new JButton("Sort by Points (Descending)");
        descendingBtn.setToolTipText("Sort the table using points in descending order");
        descendingBtn.setFocusPainted(false);
        descendingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDriverTable(driverTable, driverColumns);
                driverTable.setAutoCreateRowSorter(true);
                // DefaultRowSorter has the sort() method
                DefaultRowSorter sorter = ((DefaultRowSorter) driverTable.getRowSorter());
                ArrayList list = new ArrayList();
                list.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
                sorter.setSortKeys(list);
                sorter.sort();
            }
        });

        JButton ascendingBtn = new JButton("Sort by Points (Ascending)");
        ascendingBtn.setToolTipText("Sort the table using points in ascending order");
        ascendingBtn.setFocusPainted(false);
        ascendingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDriverTable(driverTable, driverColumns);
                driverTable.setAutoCreateRowSorter(true);
                // DefaultRowSorter has the sort() method
                DefaultRowSorter sorter = ((DefaultRowSorter) driverTable.getRowSorter());
                ArrayList list = new ArrayList();
                list.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
                sorter.setSortKeys(list);
                sorter.sort();
            }
        });

        JButton positionBtn = new JButton("Sort by First Places");
        positionBtn.setToolTipText("Sort the table using first places in descending order");
        positionBtn.setFocusPainted(false);
        positionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDriverTable(driverTable, driverColumns);
                driverTable.setAutoCreateRowSorter(true);
                // DefaultRowSorter has the sort() method
                DefaultRowSorter sorter = ((DefaultRowSorter) driverTable.getRowSorter());
                ArrayList list = new ArrayList();
                list.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
                sorter.setSortKeys(list);
                sorter.sort();
            }
        });

        JPanel driverButtons = new JPanel();
        driverButtons.add(descendingBtn);
        driverButtons.add(ascendingBtn);
        driverButtons.add(positionBtn);


        //Panel Layout
        driverPanel.add(driverScrollPane, BorderLayout.NORTH);
        driverPanel.add(driverButtons, BorderLayout.SOUTH);
//----------------------------------------------------Race Panel----------------------------------------------------
        //Create panel 2
        JPanel racePanel = new JPanel(new BorderLayout());

        // Races Table
        String raceColumns[] = {"Date", "#1 Position", "#2 Position", "#3 Position"};
        JTable raceTable = new JTable(loadDataRaceTable(racesList), raceColumns);
        raceTable.setDefaultEditor(Object.class, null);
        JTableHeader raceTblHeader = raceTable.getTableHeader();
        raceTblHeader.setBackground(Color.gray);
        raceTblHeader.setForeground(Color.white);
        raceTblHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 14));

        JScrollPane raceScrollPane = new JScrollPane(raceTable);
        raceTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        raceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                int foundIndex = 0;
                for (Race thisRace: racesList){
                    if(thisRace.getRaceDate().equalsIgnoreCase(raceTable.getValueAt(raceTable.getSelectedRow(), 0).toString()));
                }
                System.out.println(racesList.get(foundIndex).viewRaceDetails());
            }
        });

        //Race Detail Table
        String raceDetailColumns[] = {"Position", "Start Position", "Place"};
        JTable raceDetailTable = new JTable(racesList.get(0).displayStartPositions(), raceDetailColumns);
        raceDetailTable.setDefaultEditor(Object.class, null);
        JTableHeader raceDtlTblHeader = raceDetailTable.getTableHeader();
        raceDtlTblHeader.setBackground(Color.gray);
        raceDtlTblHeader.setForeground(Color.white);
        raceDtlTblHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 14));

        JScrollPane raceDtlScrollPane = new JScrollPane(raceDetailTable);
        raceDetailTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        //Buttons
        JButton randomRaceBtn = new JButton("Add new race");
        randomRaceBtn.setToolTipText("New random race will be added");
        randomRaceBtn.setFocusPainted(false);
        randomRaceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDriverTable(driverTable, driverColumns);
                addNewRace(driversList, driversList);
                TableModel raceTableModel = new DefaultTableModel(loadDataRaceTable(racesList), raceColumns);
                raceTable.setModel(raceTableModel);
            }
        });

        JButton randomProbRaceBtn = new JButton("Add new probability race");
        randomProbRaceBtn.setToolTipText("New random race according to probabilities will be added");
        randomProbRaceBtn.setFocusPainted(false);
        randomProbRaceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Shuffles the existing array list to get random starting positions
                ArrayList<Formula1Driver> startPositionsArray = new ArrayList<>(driversList);
                Collections.shuffle(startPositionsArray);

                ArrayList<Formula1Driver> probabilityArray = new ArrayList<>();
                // 1st position player has 4/10 prob
                probabilityArray.add(startPositionsArray.get(0));
                probabilityArray.add(startPositionsArray.get(0));
                probabilityArray.add(startPositionsArray.get(0));
                probabilityArray.add(startPositionsArray.get(0));

                // 2nd position player has 3/10 prob
                probabilityArray.add(startPositionsArray.get(1));
                probabilityArray.add(startPositionsArray.get(1));
                probabilityArray.add(startPositionsArray.get(1));

                // 3rd position player has 1/10 prob
                probabilityArray.add(startPositionsArray.get(2));

                // 4th position player has 1/10 prob
                probabilityArray.add(startPositionsArray.get(3));

                //5th - 9th has 0.2/10 probability
                Random randomInt = new Random();
                int randomIndex = randomInt.nextInt(5);
                // Adding a random player who is between 5th and 9th position
                probabilityArray.add(startPositionsArray.get(randomIndex + 4));

                // Shuffling the probability array to get the first position to random player with probabilities
                Collections.shuffle(probabilityArray);

                // LinkedHashSet is used to avoid object duplication
                Set<Formula1Driver> set = new LinkedHashSet<>();
                // Setting the first place to position probability
                set.add(probabilityArray.get(0));
                set.addAll(startPositionsArray);
                ArrayList<Formula1Driver> finalPlacesArray = new ArrayList<>(set);

                refreshDriverTable(driverTable, driverColumns);
                addNewRace(finalPlacesArray, startPositionsArray);
                TableModel raceTableModel = new DefaultTableModel(loadDataRaceTable(racesList), raceColumns);
                raceTable.setModel(raceTableModel);
            }
        });

        JPanel raceButtons = new JPanel();
        raceButtons.add(randomRaceBtn);
        raceButtons.add(randomProbRaceBtn);

        //Panel Layout
        racePanel.add(raceScrollPane, BorderLayout.WEST);
        racePanel.add(raceDtlScrollPane, BorderLayout.EAST);
        racePanel.add(raceButtons, BorderLayout.SOUTH);

//----------------------------------------------------About me Panel----------------------------------------------------
        //Create panel 3
        JPanel p3 = new JPanel();


//----------------------------------------------------Frame properties--------------------------------------------------
        //Create the tab container
        JTabbedPane tabs = new JTabbedPane();
        //Set tab container position
        tabs.setBounds(5, 15, 1257, 665);
        //Associate each panel with the corresponding tab
        tabs.add("Driver Details", driverPanel);
        tabs.add("Race Details", racePanel);
        tabs.add("About Me", p3);


        //Add tabs to the frame
        this.add(tabs, BorderLayout.CENTER);

        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    private Object[][] loadDataDriverTable(ArrayList<Formula1Driver> dataList) {
        Object driverData[][] = new Object[dataList.size()][8];
        for (Formula1Driver thisDriver : dataList) {
            int thisCount = dataList.indexOf(thisDriver);

            driverData[thisCount] = new Object[]{thisDriver.getTeam().getCarManufacturer(), thisDriver.getName(),
                    thisDriver.getLocation(), thisDriver.getTotalPoints(), thisDriver.getTotalRaces(),
                    thisDriver.getPositionDetails()[0], thisDriver.getPositionDetails()[1],
                    thisDriver.getPositionDetails()[2]};
        }
        return driverData;
    }

    private Object[][] loadDataRaceTable(ArrayList<Race> dataList) {
        Object raceData[][] = new Object[dataList.size()][8];
        for (Race thisRace : racesList) {
            int thisCount = dataList.indexOf(thisRace);
            raceData[thisCount] = new Object[]{thisRace.loadRaceTable()[0], thisRace.loadRaceTable()[1],
                    thisRace.loadRaceTable()[2], thisRace.loadRaceTable()[3]};
        }
        return raceData;
    }

    private void refreshDriverTable(JTable tableName, String[] columns) {
        // By default, the all the values are stored as objects so when we sort columns it gets sorted as string
        // By overriding the original tableModel we can change values at specific columns into int, so they
        // are sorted as needed
        TableModel driverTableModel = new DefaultTableModel(loadDataDriverTable(driversList), columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // The index of the column that need to be class changed
                if (columnIndex == 3 || columnIndex == 4 || columnIndex == 5 || columnIndex == 6 || columnIndex == 7)
                    return Integer.class;
                return super.getColumnClass(columnIndex);
            }
        };
        tableName.setModel(driverTableModel);
    }

    @Override
    public void readFromFile() {
        String driverDataPath = new File("src/data/driverData.ser").getAbsolutePath();
        String raceDataPath = new File("src/data/raceData.ser").getAbsolutePath();
        FileHandler readHandler = new FileHandler();

        //Reads driver related data from the driver save file
        driversList = (readHandler.readObjectFile(driverDataPath));

        //Reads race related data from the race save file
        racesList = (readHandler.readObjectFile(raceDataPath));
    }

    @Override
    public void addNewRace(ArrayList<Formula1Driver> driversListInRace, ArrayList<Formula1Driver> driversPositionsInRace) {
        //Cloning the original driver list to shuffle the array
        ArrayList<Formula1Driver> newArrayList = new ArrayList<>(driversListInRace);
        Collections.shuffle(newArrayList);
        Race shuffledRace = new Race(newArrayList, driversPositionsInRace);
        racesList.add(shuffledRace);
    }
}

public class GUI_Interface{
    public static void main(String[] args) {
        MainFrame mainWindow = new MainFrame("Formula 1 Championship Manager");
        mainWindow.setVisible( true );
    }
}