package doanjava;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GiaoDienSinhVien extends JFrame{
    private String userID;
    private String password;
    
   
        public GiaoDienSinhVien(String a,String b){
            this.userID=a;
            this.password=b;
            unitGUI();
        }
        
        public void unitGUI(){
            setSize(900,550);
            setLocationRelativeTo(null);
            setTitle("Ứng Dụng Quản Lý Sinh Viên");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            Font font = new Font("Arial", Font.BOLD, 25);
		    JLabel tieuDe=new JLabel("Màn Hình Sinh Viên");
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

        
        //Trang Chủ
        JPanel trangChu= new JPanel(new BorderLayout());
        JPanel noiDung=new JPanel(new BorderLayout());
        JTextArea NoiDung=new JTextArea();
        NoiDung.setEditable(false);
        try {
            // Mở file và đọc dữ liệu
            FileReader fileReader = new FileReader("/Users/lythanhphat9523/NetBeansProjects/DoAnJava/src/doanjava/NoiDung.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // Ghi dữ liệu vào JTextArea
                NoiDung.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        NoiDung.add(noiDung,BorderLayout.CENTER);
        trangChu.add(NoiDung,BorderLayout.CENTER);


        // Xem Điểm-center
        JPanel xemDiem = new JPanel(new BorderLayout());
        String columnXemDiem[]={"Mã Học Phần","Tên Học Phần","Số Tín Chỉ","Điểm Giữa Kỳ","%GK","Điểm Cuối Kì","%CK","Điểm Tổng Kết","Kết Quả"};
        DefaultTableModel modelXemDiem = new DefaultTableModel(columnXemDiem, 0);
        JTable bangDiem=new JTable(modelXemDiem);
        bangDiem.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDiem = new JScrollPane(bangDiem);
        xemDiem.add(scrollPaneDiem,BorderLayout.CENTER);

        // Xem Điểm-bot
        JLabel diemtb=new JLabel("Điểm Trung Bình Hệ 10: ");
        JLabel diemtb4=new JLabel("Điểm Trung Bình Hệ 4: ");
        JLabel tinChi=new JLabel("Số Tín Chỉ Tích Luỹ: ");
        JPanel TongKet=new JPanel();
        BoxLayout layout=new BoxLayout(TongKet,BoxLayout.Y_AXIS);
        TongKet.setLayout(layout);
        TongKet.add(diemtb);
        TongKet.add(diemtb4);
        TongKet.add(tinChi);
        xemDiem.add(TongKet,BorderLayout.SOUTH);


        //DKMH
        JPanel DKMH = new JPanel(new BorderLayout());
        JPanel Loc=new JPanel();
        Loc.setLayout(new BoxLayout(Loc,BoxLayout.X_AXIS));
        JLabel lb1=new JLabel("Mã Học Phần: ");
        JTextField tf1=new JTextField();
        JButton bt1=new JButton("Lọc Học Phần");
        Loc.add(lb1);
        Loc.add(tf1);
        Loc.add(bt1);
        JPanel chonLoc=new JPanel(new GridLayout(1,2));
        chonLoc.add(Loc);
        chonLoc.add(new JPanel());
        String columnDKMH[]={"Mã Học Phần","Tên Học Phần","Tiết Bắt Đầu","Số Tiết","Giảng Viên","Ngày Bắt Đầu"};
        DefaultTableModel modelDKMH = new DefaultTableModel(columnDKMH, 0);
        JTable bangDKMH=new JTable(modelDKMH);
        bangDKMH.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDKMH = new JScrollPane(bangDKMH);
        DKMH.add(scrollPaneDKMH,BorderLayout.CENTER);
        DKMH.add(chonLoc,BorderLayout.NORTH);
        
        //Xem TKB
        JPanel xemTKB=new JPanel(new BorderLayout());
        JLabel TKB=new JLabel("Thời Khóa Biểu");
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
        
        JScrollPane scrollpaneTKB= new JScrollPane();
        scrollpaneTKB.add(timetableTable);
        scrollpaneTKB.setPreferredSize(new Dimension(0,140));

        timetableTable.setBorder(border);
        JPanel pnROng=new JPanel();
        pnROng.setPreferredSize(new Dimension(0,132));
        

        xemTKB.add(new JScrollPane(timetableTable),BorderLayout.CENTER);
        xemTKB.add(titlePnTKB,BorderLayout.NORTH);
        xemTKB.add(pnROng,BorderLayout.SOUTH);


                                            //Thông Tin Cá Nhân
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
        titleTTCN.setPreferredSize(new Dimension(0,50));
        TTCN.add(titleTTCN,BorderLayout.NORTH);
        
        //CENTER
        JPanel pnTTCN= new JPanel();
        pnTTCN.setLayout(new BoxLayout(pnTTCN,BoxLayout.Y_AXIS));
        JPanel pnTTCN1=new JPanel(new GridLayout(7,1));
        
        //CENTER-MaSV
        JPanel MaSV=new JPanel(new FlowLayout(FlowLayout.LEFT,30,2));
        JLabel maSv=new JLabel("Mã Sinh Viên");
        maSv.setPreferredSize(new Dimension(100,30));
        JLabel tfTTCN1 =new JLabel();
        tfTTCN1.setPreferredSize(new Dimension(200,30));
	tfTTCN1.setText(userID);
        JPanel TT1=new JPanel(new BorderLayout());
        TT1.add(tfTTCN1,BorderLayout.CENTER);
        MaSV.add(maSv);
        MaSV.add(TT1);
        pnTTCN1.add(MaSV);
        
        //CENTER-TenSV
        JPanel TenSV=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
        JLabel TenSv=new JLabel("Họ Và Tên");
        TenSv.setPreferredSize(new Dimension(100,30));
        TenSv.setSize(100,100);
	JTextField tfTTCN2 =new JTextField();
        tfTTCN2.setPreferredSize(new Dimension(200,30));
        TenSV.add(TenSv);
        TenSV.add(tfTTCN2);
        pnTTCN1.add(TenSV);
        
        //CENTER-Nam Sinh
        JPanel NamSinh=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
        JLabel namSinh=new JLabel("Năm Sinh");
        namSinh.setPreferredSize(new Dimension(100,30));
        namSinh.setSize(100,100);
        JTextField tfTTCN3 =new JTextField();
        tfTTCN3.setPreferredSize(new Dimension(130,30));
        NamSinh.add(namSinh);
        NamSinh.add(tfTTCN3);
        pnTTCN1.add(NamSinh);

        //CENTER-Giới Tính
        JPanel GioiTinh=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
        JLabel gioitinh=new JLabel("Giới Tính");
        gioitinh.setPreferredSize(new Dimension(100,30));
        JPanel tfTTCN4 =new JPanel(new FlowLayout());
        JRadioButton nam = new JRadioButton("Nam");
        JRadioButton nu = new JRadioButton("Nữ");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(nam);
        genderGroup.add(nu);
        tfTTCN4.add(nam);
        tfTTCN4.add(nu);
        GioiTinh.add(gioitinh);
        GioiTinh.add(tfTTCN4);
        pnTTCN1.add(GioiTinh);

        //CENTER-Dân Tộc
        JPanel DanToc=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
        JLabel dantoc=new JLabel("Dân Tộc");
        dantoc.setPreferredSize(new Dimension(100,30));
        JTextField tfTTCN5 =new JTextField();
        tfTTCN5.setPreferredSize(new Dimension(80,30));
        DanToc.add(dantoc);
        DanToc.add(tfTTCN5);
        pnTTCN1.add(DanToc);
        
        //CENTER-CCCD
        JPanel CCCD=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
        JLabel cccd=new JLabel("CCCD/CMND");
        cccd.setPreferredSize(new Dimension(100,30));
        JTextField tfTTCN6 =new JTextField();
        tfTTCN6.setPreferredSize(new Dimension(200,30));
        CCCD.add(cccd);
        CCCD.add(tfTTCN6);
        pnTTCN1.add(CCCD);
        
        //CENTER-Sđt
        JPanel SDT=new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
        JLabel sdt=new JLabel("Số Điện Thoại");
        sdt.setPreferredSize(new Dimension(100,30));
        JTextField tfTTCN7 =new JTextField();
        tfTTCN7.setPreferredSize(new Dimension(160,30));
        SDT.add(sdt);
        SDT.add(tfTTCN7);
        pnTTCN1.add(SDT);
        
//        pnTTCN.add(Box.createVerticalGlue());
        pnTTCN.add(pnTTCN1);
//        pnTTCN.add(Box.createVerticalGlue());
        TTCN.add(pnTTCN,BorderLayout.CENTER);
        
        JPanel pnlChinhSua = new JPanel(new FlowLayout());
        JButton btSua= new JButton("Xác Nhận Chỉnh Sửa");
        JButton btDoiMK= new JButton("Đổi Mật Khẩu");
        btDoiMK.addActionListener(e -> {
            JFrame fDoiMK= new JFrame("Đổi Mật Khẩu");
            fDoiMK.setLayout(new BorderLayout());
            fDoiMK.setSize(450,150);
            
            JPanel titlePnMK2=new JPanel(new FlowLayout());
            titlePnMK2.setPreferredSize(new Dimension(0,50));
            JButton btXacNhan=new JButton("Xác Nhận Thay Đổi");
            titlePnMK2.add(btXacNhan);
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
        pnlChinhSua.setPreferredSize(new Dimension(0,40));
        TTCN.add(pnlChinhSua,BorderLayout.SOUTH);

        JPanel pnlRong1= new JPanel();
        pnlRong1.setPreferredSize(new Dimension(180,0));
        TTCN.add(pnlRong1,BorderLayout.WEST);

        JPanel pnlRong2=new JPanel();
        pnlRong2.setPreferredSize(new Dimension(180,0));
        TTCN.add(pnlRong2,BorderLayout.EAST);




        
        // Thêm Vô tabbedPane
        JTabbedPane thanhChon = new JTabbedPane();
        thanhChon.addTab("Trang Chủ",trangChu);
        thanhChon.addTab("Xem Điểm", xemDiem);
        thanhChon.addTab("TKB", xemTKB);
        thanhChon.addTab("ĐKMH", DKMH);
        thanhChon.addTab("Thông Tin Cá Nhân", TTCN);

        
        
        //Thêm Vào Jframe
        add(titlePn,BorderLayout.NORTH);
        add(thanhChon,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.SOUTH);
	}
}