package DoAn;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class GiaoDienDNSinhVien extends JFrame {
	public GiaoDienDNSinhVien(){
		unitGUI();
	}

	JRadioButton radio1;
	private JTextField khungDN;
	private JPasswordField khungMK;
	private JRadioButton radio2,radio3;

    public JTextField getKhungDN(){
        return this.khungDN;
    }

    public void setKhungDN(JTextField a){
        this.khungDN= a;
    }

    public JPasswordField getKhungMK(){
        return this.khungMK;
    }

	public void unitGUI(){

									//-------------------//
									//Giao Diện Đăng Nhập//
									//-------------------//
		setSize(500,300);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Ứng Dụng Quản Lý Sinh Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							

									// Màn Hình Sinh Viên
		//Top---Màn Hình Đăng Nhập
		
		JLabel tieuDe=new JLabel("Màn Hình Đăng Nhập");
		tieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
		tieuDe.setAlignmentY(Component.CENTER_ALIGNMENT);
		Font font = new Font("Arial", Font.BOLD, 25);
		tieuDe.setFont(font);
		JPanel titlePn=new JPanel();
		titlePn.setLayout(new BoxLayout(titlePn, BoxLayout.Y_AXIS));
		titlePn.add(Box.createVerticalGlue());
		titlePn.add(tieuDe);
		titlePn.add(Box.createVerticalGlue());
		titlePn.setPreferredSize(new Dimension(0,60));

		//Center----Màn Hình Đăng Nhập
		//Lựa Chọn
		radio1 = new JRadioButton("Sinh Viên");
        radio2 = new JRadioButton("Giảng Viên");
        radio3 = new JRadioButton("Khoa");
		ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
		JPanel luaChon=new JPanel();
		luaChon.setLayout(new GridLayout(1,3));
		luaChon.add(radio1);
		luaChon.add(radio2);
		luaChon.add(radio3);

		//Khung Đăng Nhập
		JPanel DangNhap=new JPanel();
		DangNhap.setLayout(new GridLayout(5,1));
        JLabel tenDN=new JLabel("Tên Đăng Nhập:");
		khungDN=new JTextField();
		khungDN.setPreferredSize(new Dimension(0,0));
		JLabel MatKhau=new JLabel("Mật Khẩu:");
		khungMK=new JPasswordField();
		khungMK.setPreferredSize(new Dimension(0,0));
		DangNhap.add(luaChon);
		DangNhap.add(tenDN);
		DangNhap.add(this.khungDN);
		DangNhap.add(MatKhau);
		DangNhap.add(this.khungMK);
		
		JLabel pnlRONG1=new JLabel();
		pnlRONG1.setPreferredSize(new Dimension(100,0));
		JLabel pnlRONG2=new JLabel();
		pnlRONG2.setPreferredSize(new Dimension(100,0));

		//Bot ------- Màn Hình Đăng Nhập
		JButton btDangNhap= new JButton("Đăng Nhập");
        btDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String TK=khungDN.getText();
                char[] MK=khungMK.getPassword();
                String MKhau=new String(MK);
                if (radio1.isSelected()) {
                    
                    if (TK.isEmpty() || MKhau.isEmpty())	JOptionPane.showMessageDialog(new JFrame(), "Tài khoản hoặc mật khẩu của bạn không hợp lệ!!!");
                    else{

                        new GiaoDienSinhVien();
                        dispose();
                    }
                } else if (radio2.isSelected()) {
                    if (TK.isEmpty() || MKhau.isEmpty())	JOptionPane.showMessageDialog(new JFrame(), "Tài khoản hoặc mật khẩu của bạn không hợp lệ!!!");
                    else{
                        System.out.println("Giảng Viên");
                        dispose();
                    }
                } else if (radio3.isSelected()) {
                    if (TK.isEmpty() || MKhau.isEmpty())	JOptionPane.showMessageDialog(new JFrame(), "Tài khoản hoặc mật khẩu của bạn không hợp lệ!!!");
                    else{
                        System.out.println("Khoa");
                        dispose();
                    }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "Vui Lòng Chọn Loại Đối Tượng");
            }
        });
		JLabel label = new JLabel("<html><u>Quên Mật Khẩu</u></html>",JLabel.CENTER);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Label clicked!");
            }
        });
		JPanel btBot=new JPanel(new GridLayout(1,2,10,10));
		btBot.add(btDangNhap);
		btBot.add(label);
		btBot.setPreferredSize(new Dimension(0,50));


		JPanel panel=new JPanel(new BorderLayout());
		panel.add(titlePn,BorderLayout.NORTH);
		panel.add(DangNhap,BorderLayout.CENTER);
		panel.add(pnlRONG1,BorderLayout.EAST);
		panel.add(pnlRONG2,BorderLayout.WEST);
		panel.add(btBot,BorderLayout.SOUTH);
		add(panel);
        setKhungDN(khungDN);
    }
        

        public static void main(String[] args){
            new GiaoDienDNSinhVien();
        }
    }

									//-------------------//
									//Giao Diện Sinh Viên//
									//-------------------//

	class GiaoDienSinhVien extends JFrame{
        
        public GiaoDienSinhVien(){
            unitGUI();
        }
        
        public void unitGUI(){
        GiaoDienDNSinhVien DNSV= new GiaoDienDNSinhVien();
            setSize(800,450);
            setVisible(true);
            setLocationRelativeTo(null);
            setTitle("Ứng Dụng Quản Lý Sinh Viên");
            setLayout(new BorderLayout());

            Font font = new Font("Arial", Font.BOLD, 25);
		    JLabel tieuDe=new JLabel("Màn Hình Sinh Viên");
            tieuDe.setFont(font);
            JLabel Xinchao=new JLabel("  Xin Chào "+"("+")");
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
        JPanel trangChu= new JPanel();


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

        timetableTable.setBorder(border);
        JPanel pnROng=new JPanel();
        pnROng.setPreferredSize(new Dimension(0,32));

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
        TTCN.add(titleTTCN,BorderLayout.NORTH);
        
        //CENTER
        JPanel pnTTCN= new JPanel(new BorderLayout());
        JPanel pnTTCN1=new JPanel(new GridLayout(7,2));
        
        pnTTCN1.add(new JLabel("Mã Sinh Viên"));
        JTextField textField1 = DNSV.getKhungDN();
        JPasswordField textField2 = DNSV.getKhungMK();
        String text1 = textField1.getText();
        char[] text = textField2.getPassword();
        String text2=new String(text);
		
        JLabel tfTTCN1 =new JLabel();
		tfTTCN1.setText(text1);
        pnTTCN1.add(tfTTCN1);
        pnTTCN1.add(new JLabel("Họ Và Tên"));
		JLabel tfTTCN2 =new JLabel();
		tfTTCN2.setText(text2);
        pnTTCN1.add(tfTTCN2);
        pnTTCN1.add(new JLabel("Năm Sinh"));
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

        pnTTCN1.add(new JLabel("Dân Tộc"));
        JTextField tfTTCN5 =new JTextField();
        pnTTCN1.add(tfTTCN5);
        pnTTCN1.add(new JLabel("CCCD/CMND"));
        JTextField tfTTCN6 =new JTextField();
        pnTTCN1.add(tfTTCN6);
        pnTTCN1.add(new JLabel("Số Điện Thoại"));
        JTextField tfTTCN7 =new JTextField();
        pnTTCN1.add(tfTTCN7);
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





