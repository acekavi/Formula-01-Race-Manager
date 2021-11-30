import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;

class MainFrame extends JFrame implements ChampionshipManager {
    ArrayList<Formula1Driver> driversList = new ArrayList<>();
    ArrayList<Race> racesList = new ArrayList<>();

    MainFrame(String title) {
        super(title);
//----------------------------------------------------Driver Panel----------------------------------------------------
        JPanel driverPanel = new JPanel(new BorderLayout());

        //Table
        String[] driverColumns = {"Team", "Driver", "Location", "Points", "Races", "1st places", "2nd places",
                "3rd places"};
        JTable driverTable = new JTable();
        readFromFile();
        //Adding sample drivers to test the program if no drivers available in the savefile
        if(driversList.isEmpty()){
            driversList.add(new Formula1Driver("Lorem", "Lorem", new Car("Lorem")));
            driversList.add(new Formula1Driver("Ipsum", "Ipsum", new Car("Ipsum")));
            driversList.add(new Formula1Driver("Dolor", "Dolor", new Car("Dolor")));
            driversList.add(new Formula1Driver("Sit", "Sit", new Car("Sit")));
            driversList.add(new Formula1Driver("Amet", "Amet", new Car("Amet")));
            driversList.add(new Formula1Driver("Consectetur", "Consectetur", new Car("Consectetur")));
            driversList.add(new Formula1Driver("Elit", "Elit", new Car("Elit")));
            driversList.add(new Formula1Driver("Sed", "Sed", new Car("Sed")));
            driversList.add(new Formula1Driver("Tempor", "Tempor", new Car("Tempor")));
            driversList.add(new Formula1Driver("Aliqua", "Aliqua", new Car("Aliqua")));
        }
        if(racesList.isEmpty()){
            Race emptyRace = new Race(driversList, driversList);
            racesList.add(emptyRace);
        }
        refreshTable(driverTable, loadDataDriverTable(driversList), driverColumns);

        JScrollPane driverScrollPane = new JScrollPane(driverTable);

        //Buttons
        JButton descendingBtn = new JButton("Sort by Points (Descending)");
        descendingBtn.setToolTipText("Sort the table using points in descending order");
        descendingBtn.setFocusPainted(false);
        descendingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Formula1Driver> newArrayList = new ArrayList<>(driversList);
                newArrayList.sort(new PointsComparator());
                refreshTable(driverTable, loadDataDriverTable(newArrayList), driverColumns);
            }
        });

        JButton ascendingBtn = new JButton("Sort by Points (Ascending)");
        ascendingBtn.setToolTipText("Sort the table using points in ascending order");
        ascendingBtn.setFocusPainted(false);
        ascendingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Formula1Driver> newArrayList = new ArrayList<>(driversList);
                newArrayList.sort(new PointsComparator());
                Collections.reverse(newArrayList);
                refreshTable(driverTable, loadDataDriverTable(newArrayList), driverColumns);
            }
        });

        JButton positionBtn = new JButton("Sort by First Places");
        positionBtn.setToolTipText("Sort the table using first places in descending order");
        positionBtn.setFocusPainted(false);
        positionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable(driverTable, loadDataDriverTable(driversList), driverColumns);
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
        String raceColumns[] = {"Date", "#1 Place", "#2 Place", "#3 Place"};
        JTable raceTable = new JTable();
        refreshTable(raceTable, loadDataRaceTable(racesList), raceColumns);
        JScrollPane raceScrollPane = new JScrollPane(raceTable);

        //Race Detail Table
        String raceDetailColumns[] = {"Start Position", "Driver Name", "#Place"};
        JTable raceDetailTable = new JTable();
        refreshTable(raceDetailTable, racesList.get(0).displayStartPositions(), raceDetailColumns);
        JScrollPane raceDtlScrollPane = new JScrollPane(raceDetailTable);
        raceTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    refreshTable(raceDetailTable, racesList.get(row).displayStartPositions(), raceDetailColumns);
                }
            }
        });

        //Buttons
        JButton randomRaceBtn = new JButton("Add new race");
        randomRaceBtn.setToolTipText("New random race will be added");
        randomRaceBtn.setFocusPainted(false);
        randomRaceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewRace(driversList, driversList);
                refreshTable(raceTable, loadDataRaceTable(racesList), raceColumns);
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

                //Cloning the array before shuffling it again to randomise other places
                ArrayList<Formula1Driver> beforeShuffle = new ArrayList(startPositionsArray);
                Collections.shuffle(startPositionsArray);

                set.addAll(startPositionsArray);
                ArrayList<Formula1Driver> finalPlacesArray = new ArrayList<>(set);

                Race shuffledRace = new Race(finalPlacesArray, beforeShuffle);
                racesList.add(shuffledRace);

                refreshTable(driverTable, loadDataDriverTable(driversList), driverColumns);
                refreshTable(raceTable, loadDataRaceTable(racesList), raceColumns);
            }
        });

        JButton raceSortBtn = new JButton("Sort by Race Date");
        raceSortBtn.setToolTipText("Sort the table using race date in ascending order");
        raceSortBtn.setFocusPainted(false);
        raceSortBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                raceTable.setAutoCreateRowSorter(true);
                // DefaultRowSorter has the sort() method
                DefaultRowSorter sorter = ((DefaultRowSorter) raceTable.getRowSorter());
                ArrayList list = new ArrayList();
                list.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                sorter.setSortKeys(list);
                sorter.sort();
            }
        });

        JPanel raceButtons = new JPanel();
        raceButtons.add(randomRaceBtn);
        raceButtons.add(randomProbRaceBtn);
        raceButtons.add(raceSortBtn);

        // Search bar
        JPanel searchPanel= new JPanel();
        JLabel labelBtn = new JLabel("Search by Driver Name: ");
        JTextField driverSearchTxt = new JTextField(10);

        //Search Detail Table
        String searchDetailColumns[] = {"Race Date", "Driver", "Start Position", "# Place"};
        String[][] nullArray = new String[0][4];
        JTable searchDetailTable = new JTable();
        refreshTable(searchDetailTable, nullArray, searchDetailColumns);
        JScrollPane searchDtlScrollPane = new JScrollPane(searchDetailTable);

        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String driverName = driverSearchTxt.getText();

                    ArrayList<Race> searchArray = new ArrayList<>();
                    for (Race thisRace: racesList){
                        if(thisRace.searchDriver(driverName)){
                            searchArray.add(thisRace);
                        }
                    }
                    Object[][] searchDetails = new Object[searchArray.size()][4];
                    int count = 0;
                    for (Race thisRace : searchArray){
                        searchDetails[count] = thisRace.driverRaceStats(driverName);
                        count++;
                    }

                    if(searchArray.size()==0){
                        JOptionPane.showMessageDialog(searchPanel, "No driver was found!");
                    }
                    else {
                        refreshTable(searchDetailTable, searchDetails, searchDetailColumns);
                    }
                }
                catch (Exception ex){
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(searchPanel, "No driver was found!");
                }
            }
        });

        searchPanel.add(labelBtn);
        searchPanel.add(driverSearchTxt);
        searchPanel.add(searchBtn);

        JPanel searchParentPnl = new JPanel(new BorderLayout());
        searchParentPnl.add(searchPanel, BorderLayout.NORTH);
        searchParentPnl.add(searchDtlScrollPane, BorderLayout.CENTER);

        JPanel tablesPanel= new JPanel();
        //Panel Layout
        tablesPanel.add(raceScrollPane, BorderLayout.WEST);
        tablesPanel.add(raceDtlScrollPane, BorderLayout.EAST);

        racePanel.add(tablesPanel, BorderLayout.NORTH);
        racePanel.add(searchParentPnl,BorderLayout.CENTER);
        racePanel.add(raceButtons, BorderLayout.SOUTH);

//----------------------------------------------------Frame properties--------------------------------------------------
        //Create the tab container
        JTabbedPane tabs = new JTabbedPane();
        //Set tab container position
        tabs.setBounds(5, 15, 1257, 665);

        //Associate each panel with the corresponding tab
        tabs.add("Driver Details", driverPanel);
        tabs.add("Race Details", racePanel);

        //Add tabs to the frame
        this.add(tabs, BorderLayout.CENTER);
        setDefaultLookAndFeelDecorated(true);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    private Object[][] loadDataDriverTable(ArrayList<Formula1Driver> dataList) {
        Object[][] driverData = new Object[dataList.size()][8];
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
        Object[][] raceData = new Object[dataList.size()][8];
        for (Race thisRace : racesList) {
            int thisCount = dataList.indexOf(thisRace);
            raceData[thisCount] = new Object[]{thisRace.loadRaceTable()[0], thisRace.loadRaceTable()[1],
                    thisRace.loadRaceTable()[2], thisRace.loadRaceTable()[3]};
        }
        return raceData;
    }

    private void refreshTable(JTable tableName, Object[][] tableData, String[] columns) {
        // By default, the all the values are stored as objects so when we sort columns it gets sorted as string
        // By overriding the original tableModel we can change values at specific columns into int, so they
        // are sorted as needed
        TableModel driverTableModel = new DefaultTableModel(tableData, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // The index of the column that need to be class changed
                for(int count = 0; count > tableData.length; count++){
                    if (columnIndex == count) return Integer.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        //Table styling
        tableName.setModel(driverTableModel);
        tableName.setDefaultEditor(Object.class, null);
        JTableHeader tblHeader = tableName.getTableHeader();
        tblHeader.setBackground(Color.DARK_GRAY);
        tblHeader.setForeground(Color.white);
        tblHeader.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        tableName.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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

