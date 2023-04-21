package doanjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GiaoDienKhoa extends JFrame{
    private String userID;
    private String password;
    
   
        public GiaoDienKhoa(String a,String b){
            this.userID=a;
            this.password=b;
            unitGUI();
        }
    
    public void unitGUI(){
        setSize(800,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Ứng dụng quản lí sinh viên");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Font font = new Font("Arial", Font.BOLD, 25);
        Font font1 = new Font("Arial", Font.BOLD, 15);
        
        JLabel tieuDe = new JLabel("Màn hình Khoa");
        tieuDe.setFont(font);
            
        JLabel xinchao = new JLabel("Xin chào " + "(" +userID+ ")");
        JLabel DangXuat = new JLabel("<html><u>Đăng xuất</u></html>");
            DangXuat.addMouseListener(new MouseAdapter (){
                @Override
                public void mouseClicked(MouseEvent e){
                    new GiaoDienDNSinhVien();
                    dispose();
                }
            });
            DangXuat.setAlignmentY(Component.RIGHT_ALIGNMENT);
                
        JPanel GioiThieu = new JPanel(new GridLayout(1,2,10,0));
            GioiThieu.add(xinchao);
            GioiThieu.add(DangXuat);
            GioiThieu.setBackground(new Color(213,232,212));
            tieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
            tieuDe.setAlignmentY(Component.CENTER_ALIGNMENT);
            GioiThieu.setAlignmentX(Component.CENTER_ALIGNMENT);
            GioiThieu.setAlignmentY(Component.CENTER_ALIGNMENT);
                
        JPanel titlePn = new JPanel();
            titlePn.setLayout(new BoxLayout(titlePn, BoxLayout.Y_AXIS));
            titlePn.add(Box.createVerticalGlue());
            titlePn.add(tieuDe);
            titlePn.add(GioiThieu);
            titlePn.add(Box.createVerticalGlue());
            titlePn.setPreferredSize(new Dimension(0,60));
            titlePn.setBackground(new Color(213,232,212));
            titlePn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        //Trang Chủ==========================================================================================
        JPanel trangChu= new JPanel();
            
        //Danh sách sinh viên==========================================================================================
        JPanel DSSV = new JPanel(new BorderLayout());
        JPanel Loc=new JPanel();
        Loc.setLayout(new BoxLayout(Loc,BoxLayout.X_AXIS));
        JLabel lb1=new JLabel("Chọn lớp:");
        JComboBox jcb1 = new JComboBox();
        JButton bt1=new JButton("Tìm kiếm");
        Loc.add(lb1);
        Loc.add(jcb1);
        Loc.add(bt1);
        JPanel chonLoc=new JPanel(new GridLayout(1,2 ));
        chonLoc.add(Loc);
        chonLoc.add(new JPanel());
        String columnDSSV[]={"Mã SV","Tên sinh viên","Năm sinh","Giới tính","Dân tộc","CCCD/CMND", "Số điện thoại"};
        DefaultTableModel modelDSSV = new DefaultTableModel(columnDSSV, 0);
        JTable bangDSSV=new JTable(modelDSSV);
        bangDSSV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSSV = new JScrollPane(bangDSSV);
        scrollPaneDSSV.setPreferredSize(new Dimension(0,100));
        
//        JPanel khungThongTin= new JPanel(new GridLayout(2,1));
//        JPanel khungTT1=new JPanel();
//        khungTT1.setLayout(new BoxLayout(khungTT1,BoxLayout.X_AXIS));
//        khungTT1.add(new JLabel("Mã Sinh Viên"));
//        khungTT1.add(new JTextField());
//        khungTT1.add(new JLabel("Tên Sinh Viên"));
//        khungTT1.add(new JTextField());
//        khungTT1.add(new JLabel("Năm Sinh"));
//        khungTT1.add(new JTextField());
//        khungTT1.add(new JLabel("Giới Tính"));
//        khungTT1.add(new JTextField());
//        JPanel khungTT2=new JPanel();
//        khungTT2.setLayout(new BoxLayout(khungTT2,BoxLayout.X_AXIS));
//        khungTT2.add(new JLabel("Dân Tộc"));
//        khungTT2.add(new JTextField());
//        khungTT2.add(new JLabel("CCCD/CMND"));
//        khungTT2.add(new JTextField());
//        khungTT2.add(new JLabel("Số Điện Thoại"));
//        khungTT2.add(new JTextField());
//        khungThongTin.add(khungTT1);
//        khungThongTin.add(khungTT2);
        
        DSSV.add(scrollPaneDSSV,BorderLayout.CENTER);
        DSSV.add(chonLoc,BorderLayout.NORTH);            
        
        //Danh sách giảng viên
        JPanel DSGV=new JPanel(new BorderLayout());
        JLabel GV=new JLabel("Danh sách Giảng viên");
		GV.setAlignmentX(Component.CENTER_ALIGNMENT);
		GV.setAlignmentY(Component.CENTER_ALIGNMENT);
		GV.setFont(font1);
		JPanel titlePnGV=new JPanel();
		titlePnGV.setLayout(new BoxLayout(titlePnGV, BoxLayout.Y_AXIS));
		titlePnGV.add(Box.createVerticalGlue());
		titlePnGV.add(GV);
		titlePnGV.add(Box.createVerticalGlue());
		titlePnGV.setPreferredSize(new Dimension(0,40));
        String columnDSGV[]={"Mã GV","Tên giảng viên","Trình độ","Số điện thoại","Email"};
        DefaultTableModel modelDSGV = new DefaultTableModel(columnDSGV, 0);
        JTable tableDSGV = new JTable(modelDSGV);
        tableDSGV.getTableHeader().setReorderingAllowed(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        tableDSGV.setBorder(border);
        JPanel pnDSGV=new JPanel();
        pnDSGV.setPreferredSize(new Dimension(0,25));

        DSGV.add(new JScrollPane(tableDSGV),BorderLayout.CENTER);
        DSGV.add(titlePnGV,BorderLayout.NORTH);
        DSGV.add(pnDSGV,BorderLayout.SOUTH);
        
        //DSMH
        JPanel DSMH=new JPanel(new BorderLayout());
        JLabel MH=new JLabel("Danh sách Môn học");
		MH.setAlignmentX(Component.CENTER_ALIGNMENT);
		MH.setAlignmentY(Component.CENTER_ALIGNMENT);
		MH.setFont(font1);
		JPanel titlePnMH=new JPanel();
		titlePnMH.setLayout(new BoxLayout(titlePnMH, BoxLayout.Y_AXIS));
		titlePnMH.add(Box.createVerticalGlue());
		titlePnMH.add(MH);
		titlePnMH.add(Box.createVerticalGlue());
		titlePnMH.setPreferredSize(new Dimension(0,40));
                String columnDSMH[]={"Mã Học Phần","Tên Học Phần","Tiết Bắt Đầu","Số Tiết","Giảng Viên","Ngày Bắt Đầu"};
        DefaultTableModel modelDSMH = new DefaultTableModel(columnDSMH, 0);
        JTable tableDSMH = new JTable(modelDSMH);
        tableDSMH.getTableHeader().setReorderingAllowed(false);
        Border border2 = BorderFactory.createLineBorder(Color.BLACK);

        tableDSMH.setBorder(border2);
        JPanel pnDSMH=new JPanel();
        pnDSMH.setPreferredSize(new Dimension(0,25));

        DSMH.add(new JScrollPane(tableDSMH),BorderLayout.CENTER);
        DSMH.add(titlePnMH,BorderLayout.NORTH);
        DSMH.add(pnDSMH,BorderLayout.SOUTH);
        
        //Tình trạng học phí
        JPanel TTHP=new JPanel(new BorderLayout());
        JLabel HP=new JLabel("Tình trạng Học phí");
		HP.setAlignmentX(Component.CENTER_ALIGNMENT);
		HP.setAlignmentY(Component.CENTER_ALIGNMENT);
		HP.setFont(font1);
		JPanel titlePnTTHP=new JPanel();
		titlePnTTHP.setLayout(new BoxLayout(titlePnTTHP, BoxLayout.Y_AXIS));
		titlePnTTHP.add(Box.createVerticalGlue());
		titlePnTTHP.add(HP);
		titlePnTTHP.add(Box.createVerticalGlue());
		titlePnTTHP.setPreferredSize(new Dimension(0,40));
                String columnTTHP[]={"Mã sinh viên","Tên sinh viên","Lớp", "Học kỳ","Năm học", "Học phí", "Trường hợp miễn giảm", "Tổng", "Tình trạng"};
        DefaultTableModel modelTTHP = new DefaultTableModel(columnTTHP, 0);
        JTable tableTTHP = new JTable(modelTTHP);
        tableTTHP.getTableHeader().setReorderingAllowed(false);

        tableTTHP.setBorder(border);
        JPanel pnTTHP=new JPanel();
        pnTTHP.setPreferredSize(new Dimension(0,25));

        TTHP.add(new JScrollPane(tableTTHP),BorderLayout.CENTER);
        TTHP.add(titlePnTTHP,BorderLayout.NORTH);
        TTHP.add(pnTTHP,BorderLayout.SOUTH);
        
        
        // Thêm Vô tabbedPane
        JTabbedPane thanhChon = new JTabbedPane();
        thanhChon.addTab("Trang Chủ",trangChu);
        thanhChon.addTab("Danh sách SV",DSSV);
        thanhChon.addTab("Danh sách GV",DSGV);
        thanhChon.addTab("Danh sách MH",DSMH);
        thanhChon.addTab("Học phí",TTHP);
            
        //Thêm Vào Jframe
        add(titlePn,BorderLayout.NORTH);
        add(thanhChon,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.SOUTH);       
    }
}
