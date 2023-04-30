package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GiaoDienGiangVien extends JFrame {

    private String userID;
    private String password;

    public GiaoDienGiangVien(String a, String b) {
        this.userID = a;
        this.password = b;
        unit();
    }

    public void unit() {
        setSize(900, 550);
        setLocationRelativeTo(null);
        setTitle("Ứng Dụng Quản Lý Sinh Viên");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "title.png").getImage());

        Font font = new Font("Arial", Font.BOLD, 25);
        JLabel tieuDe = new JLabel("Màn Hình Giảng Viên");
        tieuDe.setFont(font);
        JLabel Xinchao = new JLabel("  Xin Chào " + "(" + userID + ")");
        JLabel DangXuat = new JLabel("<html><u>Đăng Xuất</u></html>");
        DangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GiaoDienDNSinhVien();
                dispose();
            }
        });
        DangXuat.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JPanel GioiThieu = new JPanel(new GridLayout(1, 2, 10, 0));
        GioiThieu.add(Xinchao);
        GioiThieu.add(DangXuat);
        GioiThieu.setBackground(new Color(213, 232, 212));
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
        titlePn.setPreferredSize(new Dimension(0, 60));
        titlePn.setBackground(new Color(213, 232, 212));
        titlePn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //trang Chủ
        JPanel trangChu = new JPanel(new BorderLayout());
        JPanel noiDung = new JPanel(new BorderLayout());
        JTextArea NoiDung = new JTextArea();
        NoiDung.setEditable(false);
        try {
            // Mở file và đọc dữ liệu
            FileReader fileReader = new FileReader(".\\src\\doan\\NoiDung.txt");
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

        NoiDung.add(noiDung, BorderLayout.CENTER);
        trangChu.add(NoiDung, BorderLayout.CENTER);

        //DANH SÁCH ĐẢM NHẬN
        JPanel PN1 = new JPanel(new GridLayout(1, 2));
        JPanel PN11 = new JPanel();
        PN11.setLayout(new BoxLayout(PN11, BoxLayout.X_AXIS));
        PN11.add(new JLabel("Lựa chọn: "));
        String[] luaChon = {"Lớp Đảm Nhận", "Lớp Cố Vấn"};
        JComboBox<String> LuaChon = new JComboBox<>(luaChon);
        PN11.add(LuaChon);
        PN1.add(PN11);
        PN1.add(new JPanel());

        JPanel centerPanel = new JPanel(new BorderLayout());

        JPanel danhSachSV = new JPanel(new BorderLayout());

        // DSSV đảm nhân
        JPanel quanLySinhVienDN = new JPanel(new BorderLayout());
        quanLySinhVienDN.setLayout(new BorderLayout());
        String columnDSSV[] = {"STT", "Mã Lớp", "Mã Môn Học", "Số Tín Chỉ", "Nhóm", "Thứ", "Tiết Bắt Đầu", "Số Tiết", "Thời Gian Học", "Phòng Học", "DSSV"};
        DefaultTableModel modelDSSV = new DefaultTableModel(columnDSSV, 0);
        JTable bangDSSV = new JTable(modelDSSV);
        bangDSSV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSSV = new JScrollPane(bangDSSV);

        //DSSV Cố Vấn
        JPanel quanLySinhVienCV = new JPanel(new BorderLayout());
        quanLySinhVienCV.setLayout(new BorderLayout());
        String columnDSSVCoVan[] = {"STT", "Mã Lớp", "Tên Lớp", "DSSV"};
        DefaultTableModel modelDSSVCV = new DefaultTableModel(columnDSSVCoVan, 0);
        JTable bangDSSVCV = new JTable(modelDSSVCV);
        bangDSSVCV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSSVCV = new JScrollPane(bangDSSVCV);
        centerPanel.add(scrollPaneDSSV, BorderLayout.CENTER);
        danhSachSV.add(PN1, BorderLayout.NORTH);
        danhSachSV.add(centerPanel, BorderLayout.CENTER);

        //aaaa
        LuaChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPanel = (String) LuaChon.getSelectedItem();
                if (selectedPanel.equals("Lớp Đảm Nhận")) {
                    // Nếu chọn "Panel 1", hiển thị nội dung tương ứng trong JPanel
                    centerPanel.removeAll();
                    centerPanel.add(scrollPaneDSSV, BorderLayout.CENTER);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                } else if (selectedPanel.equals("Lớp Cố Vấn")) {
                    // Nếu chọn "Panel 2", hiển thị nội dung tương ứng trong JPanel
                    centerPanel.removeAll();
                    centerPanel.add(scrollPaneDSSVCV, BorderLayout.CENTER);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                }
            }
        });

        //lỊCH GIẢNG DẠY
        JPanel xemTKB = new JPanel(new BorderLayout());
        JLabel TKB = new JLabel("Lịch Giảng Dạy");
        TKB.setAlignmentX(Component.CENTER_ALIGNMENT);
        TKB.setAlignmentY(Component.CENTER_ALIGNMENT);
        TKB.setFont(font);
        JPanel titlePnTKB = new JPanel();
        titlePnTKB.setLayout(new BoxLayout(titlePnTKB, BoxLayout.Y_AXIS));
        titlePnTKB.add(Box.createVerticalGlue());
        titlePnTKB.add(TKB);
        titlePnTKB.add(Box.createVerticalGlue());
        titlePnTKB.setPreferredSize(new Dimension(0, 60));
        String columnXemTKB[] = {"", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật"};
        DefaultTableModel modelXemTKB = new DefaultTableModel(columnXemTKB, 12);
        JTable timetableTable = new JTable(modelXemTKB);
        timetableTable.getTableHeader().setReorderingAllowed(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        timetableTable.setBorder(border);
        JPanel pnROng = new JPanel();
        pnROng.setPreferredSize(new Dimension(0, 144));

        xemTKB.add(new JScrollPane(timetableTable), BorderLayout.CENTER);
        xemTKB.add(titlePnTKB, BorderLayout.NORTH);
        xemTKB.add(pnROng, BorderLayout.SOUTH);

        //THÔNG TIN CÁ NHÂN
        JPanel TTCN = new JPanel(new BorderLayout());
        //Top
        JLabel LabelTTCN = new JLabel("Thông Tin Cá Nhân");
        JPanel titleTTCN = new JPanel();
        LabelTTCN.setFont(font);
        LabelTTCN.setAlignmentX(Component.CENTER_ALIGNMENT);
        LabelTTCN.setAlignmentY(Component.CENTER_ALIGNMENT);
        titleTTCN.setLayout(new BoxLayout(titleTTCN, BoxLayout.Y_AXIS));
        titleTTCN.add(Box.createVerticalGlue());
        titleTTCN.add(LabelTTCN);
        titleTTCN.add(Box.createVerticalGlue());
        TTCN.add(titleTTCN, BorderLayout.NORTH);

        //CENTER
       JPanel pnTTCN = new JPanel();
        pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
        JPanel pnTTCN1 = new JPanel(new GridLayout(8, 1));

         //CENTER-MaGV
         JPanel MaGV = new JPanel();
        JLabel maGv = new JLabel("Mã Giảng Viên");
        maGv.setPreferredSize(new Dimension(100, 30));
        JLabel tfTTCN1 = new JLabel();
        tfTTCN1.setPreferredSize(new Dimension(200, 30));
        tfTTCN1.setText(userID);
        
        MaGV.add(maGv);
        MaGV.add(tfTTCN1);
        pnTTCN1.add(MaGV);
        
        
        //CENTER-TenGV
        JPanel TenGV = new JPanel(new FlowLayout());
        JLabel TenGv = new JLabel("Họ Và Tên");
        TenGv.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN2 = new JTextField();
        tfTTCN2.setPreferredSize(new Dimension(200, 30));
        TenGV.add(TenGv);
        TenGV.add(tfTTCN2);
        pnTTCN1.add(TenGV);
        
        
        //CENTER-KHOA
        JPanel KHOA = new JPanel(new FlowLayout());
        JLabel khoa = new JLabel("Khoa");
        khoa.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN3 = new JTextField();
        tfTTCN3.setPreferredSize(new Dimension(200, 30));
        KHOA.add(khoa);
        KHOA.add(tfTTCN3);
        pnTTCN1.add(KHOA);
        

        //CENTER-Giới Tính
        JPanel GioiTinh = new JPanel(new FlowLayout());
        JLabel gioitinh = new JLabel("Giới Tính");
        String[] luaChonTTCN = {"Nam", "Nữ"};
        JComboBox<String> LuaChonTTCN = new JComboBox<>(luaChonTTCN);
        gioitinh.setPreferredSize(new Dimension(100, 30));
        LuaChonTTCN.setPreferredSize(new Dimension(200, 30));
        GioiTinh.add(gioitinh);
        GioiTinh.add(LuaChonTTCN);
        pnTTCN1.add(GioiTinh);
        
        
        //CENTER-Trình Độ
        JPanel TRINHDO = new JPanel(new FlowLayout());
        JLabel trinhdo = new JLabel("Trình Độ");
        trinhdo.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN4 = new JTextField();
        tfTTCN4.setPreferredSize(new Dimension(200, 30));
        TRINHDO.add(trinhdo);
        TRINHDO.add(tfTTCN4);
        pnTTCN1.add(TRINHDO);
        
        
                //CENTER-CCCD
        JPanel CCCD = new JPanel(new FlowLayout());
        JLabel cccd = new JLabel("CCCD/CMND");
        cccd.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN5 = new JTextField();
        tfTTCN5.setPreferredSize(new Dimension(200, 30));
        CCCD.add(cccd);
        CCCD.add(tfTTCN5);
        pnTTCN1.add(CCCD);

        //CENTER-Sđt
        JPanel SDT = new JPanel(new FlowLayout());
        JLabel sdt = new JLabel("Số Điện Thoại");
        sdt.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN6 = new JTextField();
        tfTTCN6.setPreferredSize(new Dimension(200, 30));
        SDT.add(sdt);
        SDT.add(tfTTCN6);
        pnTTCN1.add(SDT);
        
        //CENTER-EMAIL
        JPanel EMAIL = new JPanel(new FlowLayout());
        JLabel email = new JLabel("Email");
        email.setPreferredSize(new Dimension(100, 30));
        JTextField tfTTCN7 = new JTextField();
        tfTTCN7.setPreferredSize(new Dimension(200, 30));
        EMAIL.add(email);
        EMAIL.add(tfTTCN7);
        pnTTCN1.add(EMAIL);
        
        
        
        pnTTCN.add(pnTTCN1, BorderLayout.CENTER);
        TTCN.add(pnTTCN, BorderLayout.CENTER);
        JPanel pnlChinhSua = new JPanel(new FlowLayout());
        JButton btSua = new JButton("Xác Nhận Chỉnh Sửa");
        JButton btDoiMK = new JButton("Đổi Mật Khẩu");
        btDoiMK.addActionListener(e -> {
            JFrame fDoiMK = new JFrame("Đổi Mật Khẩu");
            fDoiMK.setLayout(new BorderLayout());
            fDoiMK.setSize(450, 150);

            JPanel titlePnMK2 = new JPanel(new FlowLayout());
            titlePnMK2.setPreferredSize(new Dimension(0, 50));
            JButton btXacNhan = new JButton("Xác Nhận Thay Đổi");
            titlePnMK2.add(btXacNhan);
            JPanel titlePnMK3 = new JPanel();
            titlePnMK3.setPreferredSize(new Dimension(40, 0));
            JPanel titlePnMK4 = new JPanel();
            titlePnMK4.setPreferredSize(new Dimension(40, 0));
            JPanel DoiMK = new JPanel(new GridLayout(2, 2));
            DoiMK.add(new JLabel("Mật Khẩu Cũ"));
            JPasswordField DoiMkTF1 = new JPasswordField();
            DoiMK.add(DoiMkTF1);
            DoiMK.add(new JLabel("Mật Khẩu Mới"));
            JPasswordField DoiMkTF2 = new JPasswordField();
            DoiMK.add(DoiMkTF2);

            fDoiMK.add(DoiMK, BorderLayout.CENTER);
            fDoiMK.add(titlePnMK2, BorderLayout.SOUTH);
            fDoiMK.add(titlePnMK3, BorderLayout.WEST);
            fDoiMK.add(titlePnMK4, BorderLayout.EAST);
            fDoiMK.setVisible(true);
            fDoiMK.setLocationRelativeTo(null);
        });

        pnlChinhSua.add(btSua);
        pnlChinhSua.add(btDoiMK);
        pnlChinhSua.setPreferredSize(new Dimension(0, 50));
        TTCN.add(pnlChinhSua, BorderLayout.SOUTH);

        JPanel pnlRong1 = new JPanel();
        pnlRong1.setPreferredSize(new Dimension(180, 0));
        TTCN.add(pnlRong1, BorderLayout.WEST);

        JPanel pnlRong2 = new JPanel();
        pnlRong2.setPreferredSize(new Dimension(180, 0));
        TTCN.add(pnlRong2, BorderLayout.EAST);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Trang chủ", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "home_1.png"), trangChu);
        tabbedPane.addTab("Danh Sách Đảm Nhận", danhSachSV);
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