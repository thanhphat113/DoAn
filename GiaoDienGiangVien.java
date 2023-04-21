package doan;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
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

public class GiaoDienGiangVien extends JFrame{
    private String userID;
    private String password;
 
    public GiaoDienGiangVien(String a,String b)
    {
        this.userID=a;
        this.password=b;
        unit();
    }
    public void unit()
    {
        setSize( 800,450);
        setLocationRelativeTo(null);
        setTitle("Ứng Dụng Quản Lý Sinh Viên");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font font = new Font("Arial", Font.BOLD, 25);
            JLabel tieuDe=new JLabel("Màn Hình Giảng Viên");
            tieuDe.setFont(font);
            JLabel Xinchao=new JLabel("  Xin Chào "+"("+userID+")");
            JLabel DangXuat=new JLabel("<html><u>Đăng Xuất</u></html>");
            DangXuat.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new GiaoDienDNSinhVien();
                    dispose();
                }
            });
        DangXuat.setAlignmentY(Component.RIGHT_ALIGNMENT);
        JPanel GioiThieu= new JPanel(new GridLayout(1,2,10,0));
        GioiThieu.add(Xinchao);
        GioiThieu.add(DangXuat);
        GioiThieu.setBackground(new Color(213,232,212));
	tieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
	tieuDe.setAlignmentY(Component.CENTER_ALIGNMENT);
        GioiThieu.setAlignmentX(Component.CENTER_ALIGNMENT);
	GioiThieu.setAlignmentY(Component.CENTER_ALIGNMENT);
	JPanel titlePn=new JPanel();
	titlePn.setLayout(new BoxLayout(titlePn, BoxLayout.Y_AXIS));
	titlePn.add(Box.createVerticalGlue());
	titlePn.add(tieuDe);
        titlePn.add(GioiThieu);
	titlePn.add(Box.createVerticalGlue());
        titlePn.setPreferredSize(new Dimension(0,60));
        titlePn.setBackground(new Color(213,232,212));
        titlePn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        JPanel trangChupn = new JPanel();
        JLabel trangChulb = new JLabel("Trang chủ");
        
        trangChupn.add(trangChulb);


        //lỊCH GIẢNG DẠY
        JPanel xemTKB=new JPanel(new BorderLayout());
        JLabel TKB=new JLabel("Lịch Giảng Dạy");
	TKB.setAlignmentX(Component.CENTER_ALIGNMENT);
	TKB.setAlignmentY(Component.CENTER_ALIGNMENT);
	TKB.setFont(font);
	JPanel titlePnTKB=new JPanel();
	titlePnTKB.setLayout(new BoxLayout(titlePnTKB, BoxLayout.Y_AXIS));
	titlePnTKB.add(Box.createVerticalGlue());
	titlePnTKB.add(TKB);
	titlePnTKB.add(Box.createVerticalGlue());
	titlePnTKB.setPreferredSize(new Dimension(0,60));
        String columnXemTKB[]={"","Thứ Hai","Thứ Ba","Thứ Tư","Thứ Năm","Thứ Sáu","Thứ Bảy","Chủ Nhật"};
        DefaultTableModel modelXemTKB = new DefaultTableModel(columnXemTKB, 12);
        JTable timetableTable = new JTable(modelXemTKB);
        timetableTable.getTableHeader().setReorderingAllowed(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        timetableTable.setBorder(border);
        JPanel pnROng=new JPanel();
        pnROng.setPreferredSize(new Dimension(0,32));

        xemTKB.add(new JScrollPane(timetableTable),BorderLayout.CENTER);
        xemTKB.add(titlePnTKB,BorderLayout.NORTH);
        xemTKB.add(pnROng,BorderLayout.SOUTH);

        //THÔNG TIN CÁ NHÂN
        JPanel TTCN= new JPanel(new BorderLayout());
        //Top
        JLabel LabelTTCN = new JLabel("Thông Tin Cá Nhân");
        JPanel titleTTCN=new JPanel();
        LabelTTCN.setFont(font);
        LabelTTCN.setAlignmentX(Component.CENTER_ALIGNMENT);
	LabelTTCN.setAlignmentY(Component.CENTER_ALIGNMENT);
        titleTTCN.setLayout(new BoxLayout(titleTTCN, BoxLayout.Y_AXIS));
	titleTTCN.add(Box.createVerticalGlue());
	titleTTCN.add(LabelTTCN);
	titleTTCN.add(Box.createVerticalGlue());
        TTCN.add(titleTTCN,BorderLayout.NORTH);
        
        //CENTER
        JPanel pnTTCN= new JPanel(new BorderLayout());
        JPanel pnTTCN1=new JPanel(new GridLayout(8,2));
        
        pnTTCN1.add(new JLabel("Mã Giảng Viên"));
        JLabel tfTTCN1 =new JLabel();
	tfTTCN1.setText(userID);
        pnTTCN1.add(tfTTCN1);
        pnTTCN1.add(new JLabel("Họ Và Tên"));
		JLabel tfTTCN2 =new JLabel();
		tfTTCN2.setText(password);
        pnTTCN1.add(tfTTCN2);
        pnTTCN1.add(new JLabel("Khoa"));
        JTextField tfTTCN3 =new JTextField();
        pnTTCN1.add(tfTTCN3);

        //Giới Tính
        pnTTCN1.add(new JLabel("Giới Tính"));
        JPanel tfTTCN4 =new JPanel(new GridLayout(1,2));
        JRadioButton nam = new JRadioButton("Nam");
        JRadioButton nu = new JRadioButton("Nữ");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(nam);
        genderGroup.add(nu);
        tfTTCN4.add(nam);
        tfTTCN4.add(nu);
        pnTTCN1.add(tfTTCN4);

        pnTTCN1.add(new JLabel("Trình Độ"));
        JTextField tfTTCN5 =new JTextField();
        pnTTCN1.add(tfTTCN5);
        pnTTCN1.add(new JLabel("CCCD/CMND"));
        JTextField tfTTCN6 =new JTextField();
        pnTTCN1.add(tfTTCN6);
        pnTTCN1.add(new JLabel("Số Điện Thoại"));
        JTextField tfTTCN7 =new JTextField();
        pnTTCN1.add(tfTTCN7);
        pnTTCN1.add(new JLabel("Email"));
        JTextField tfTTCN8 =new JTextField();
        pnTTCN1.add(tfTTCN8);
        pnTTCN.add(pnTTCN1,BorderLayout.CENTER);
        // pnTTCN.add(new JPanel());
        TTCN.add(pnTTCN,BorderLayout.CENTER);
        JPanel pnlChinhSua = new JPanel(new GridLayout(1,2));
        JButton btSua= new JButton("Xác Nhận Chỉnh Sửa");
        JButton btDoiMK= new JButton("Đổi Mật Khẩu");
        btDoiMK.addActionListener(e -> {
            JFrame fDoiMK= new JFrame("Đổi Mật Khẩu");
            fDoiMK.setLayout(new BorderLayout());
            fDoiMK.setSize(450,150);
            
            JPanel titlePnMK2=new JPanel(new BorderLayout());
            titlePnMK2.setPreferredSize(new Dimension(0,50));
            JButton btXacNhan=new JButton("Xác Nhận Thay Đổi");
            titlePnMK2.add(btXacNhan,BorderLayout.CENTER);
            JPanel titlePnMK3=new JPanel();
            titlePnMK3.setPreferredSize(new Dimension(40,0));
            JPanel titlePnMK4=new JPanel();
            titlePnMK4.setPreferredSize(new Dimension(40,0));
            JPanel DoiMK=new JPanel(new GridLayout(2,2));
            DoiMK.add(new JLabel("Mật Khẩu Cũ"));
            JPasswordField DoiMkTF1= new JPasswordField();
            DoiMK.add(DoiMkTF1);
            DoiMK.add(new JLabel("Mật Khẩu Mới"));
            JPasswordField DoiMkTF2= new JPasswordField();
            DoiMK.add(DoiMkTF2);
            
            fDoiMK.add(DoiMK,BorderLayout.CENTER);
            fDoiMK.add(titlePnMK2,BorderLayout.SOUTH);
            fDoiMK.add(titlePnMK3,BorderLayout.WEST);
            fDoiMK.add(titlePnMK4,BorderLayout.EAST);
            fDoiMK.setVisible(true);
            fDoiMK.setLocationRelativeTo(null);
            });


        pnlChinhSua.add(btSua);
        pnlChinhSua.add(btDoiMK);
        pnlChinhSua.setPreferredSize(new Dimension(0,50));
        TTCN.add(pnlChinhSua,BorderLayout.SOUTH);

        JPanel pnlRong1= new JPanel();
        pnlRong1.setPreferredSize(new Dimension(180,0));
        TTCN.add(pnlRong1,BorderLayout.WEST);

        JPanel pnlRong2=new JPanel();
        pnlRong2.setPreferredSize(new Dimension(180,0));
        TTCN.add(pnlRong2,BorderLayout.EAST);
      

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Trang chủ", trangChupn);
        tabbedPane.addTab("Lịch trình dạy học", xemTKB);
        tabbedPane.addTab("Thông tin giảng viên", TTCN);

        JPanel mainPN = new JPanel();
        mainPN.setLayout(new BorderLayout());
        mainPN.add(titlePn, BorderLayout.NORTH);
        mainPN.add(tabbedPane, BorderLayout.CENTER);


        add(mainPN);
       

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}