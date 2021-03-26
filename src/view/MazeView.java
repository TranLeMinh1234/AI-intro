/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import controller.FindingProcess;
import entity.*;
/**
 *
 * @author acer
 */
public class MazeView extends javax.swing.JFrame{
    public static int left = 200, top = 140;
    public static int right, bottom;
    
    private final int box_width, box_height;
    private final int rows, columns;
    
    private javax.swing.JLayeredPane mazePane;
    private FindingProcess process;
    
    public MazeView(int width, int height, int row, int column) {
        rows = row;
        columns = column;
        box_width = width/column;
        box_height = height/row;
        
        right = left + width;
        bottom = top + height;
        
        initComponents(width, height);
        process = new FindingProcess();
        process.setMaze(new Maze(row, column));
    }
    
    public MazeView(int width, int height, int row, int column, short[][] mazeDetail) {
        rows = row;
        columns = column;
        box_width = width/column;
        box_height = height/row;
        
        right = left + width;
        bottom = top + height;
        
        initComponents(width, height);
        process = new FindingProcess();
        process.getMaze().setMaze(mazeDetail);
    }
    
    private void initComponents(int width, int height) {
        this.setResizable(false);
        this.setLocation(left, top);
        
        mazePane = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(mazePane);
        mazePane.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, width, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, height, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mazePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mazePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    private final void showMaze() {
        short[][] mazeInfo = this.process.getMaze().getMaze();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                switch(mazeInfo[i][j]) {
                    case DemoObject.WALL: 
                        mazePane.add(new Wall(box_height*i, box_width*j, box_width, box_height), columns*i + j);
                        break;                          
                    case DemoObject.BOT:
                        mazePane.add(new Way(box_height*i, box_width*j, box_width, box_height), columns*i + j);
                        process.setBot(new Bot(box_height*i, box_width*j, box_width, box_height, 5));
                        break;
                    case DemoObject.GOAL:
                        process.setGoal(new Goal(box_height*i, box_width*j, box_width, box_height, 5));
                    case DemoObject.WAY: 
                        mazePane.add(new Way(box_height*i, box_width*j, box_width, box_height), columns*i + j);
                        break;
                    default:
                }
            }
        }
        
        try {
            mazePane.add(process.getBot());
            mazePane.add(process.getGoal());
        } catch(Exception e) {
            System.out.println("Haven't init bot and goal yet");
        }
    }
    
    public final void findGoal() {
       this.setVisible(true);
       showMaze();
    }
    
    public final FindingProcess getProcess() {
        return this.process;
    }
    
    public final MazeView cloneNew(int width, int height, int row, int column) {
        this.setVisible(false);
        this.dispose();
        
        return new MazeView(width, height, row, column);
    }
}
