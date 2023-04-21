# DoAn
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GiaoDienGiangVien extends JFrame{
 
    final private Font mainFont = new Font("Arial", Font.BOLD, 25);
    final private Font miniFont = new Font("Arial", Font.BOLD, 17);
    public GiaoDienGiangVien()
    {
        unit();
    }
    public void unit()
    {
        setSize(1000, 800);
        setTitle("Giao diện dành cho giảng viên");
        setLocationRelativeTo(null);

        JLabel giangVienlb = new JLabel("Giảng Viên", JLabel.CENTER);
        giangVienlb.setFont(mainFont);
        JPanel giangVienpn = new JPanel();
        giangVienpn.add(giangVienlb);

        JPanel trangChupn = new JPanel();
        JLabel trangChulb = new JLabel("Trang chủ");
        trangChulb.setFont(mainFont);
        trangChupn.add(trangChulb);

        JPanel danhSachSV = new JPanel(new BorderLayout());
        String columnDSSV[]={"STT","Mã số sinh viên","Họ tên sinh viên","Năm sinh","Khoa","Lớp","Số điện thoại","Email"};
        DefaultTableModel modelDSSV = new DefaultTableModel(columnDSSV, 0);
        JTable DSSV = new JTable(modelDSSV);
        JScrollPane scrollPaneDSSV = new JScrollPane(DSSV);
        danhSachSV.add(scrollPaneDSSV,BorderLayout.CENTER);

        JLabel maSVlb = new JLabel("Mã số sinh viên: ");
        maSVlb.setFont(miniFont);
        JTextField maSVtf = new JTextField(20);
        JLabel tenSVlb = new JLabel("Họ tên sinh viên: ");
        tenSVlb.setFont(miniFont);
        JTextField tenSVtf = new JTextField(20);
        JLabel namSinhSVlb = new JLabel("Năm sinh: ");
        namSinhSVlb.setFont(miniFont);
        JLabel khoaSVlb = new JLabel("Khoa: ");
        khoaSVlb.setFont(miniFont);
        JLabel lopSVlb = new JLabel("Lớp: ");
        lopSVlb.setFont(miniFont);
        JLabel sDTSVlb = new JLabel("Số điện thoại: ");
        sDTSVlb.setFont(miniFont);
        JTextField sDTSVtf = new JTextField(20);
        JLabel emailSVlb = new JLabel("Email: ");
        emailSVlb.setFont(miniFont);
        JTextField emailSVtf = new JTextField(20);
        JPanel maSVpn = new JPanel();
        maSVpn.setLayout(new FlowLayout(10, 10, 10));
        maSVpn.add(maSVlb);
        maSVpn.add(maSVtf);
        JPanel tenSVpn = new JPanel();
        tenSVpn.setLayout(new FlowLayout(10, 10, 10));
        tenSVpn.add(tenSVlb);
        tenSVpn.add(tenSVtf);
        JPanel namSinhSVpn = new JPanel();
        namSinhSVpn.setLayout(new FlowLayout(10, 10, 10));
        namSinhSVpn.add(namSinhSVlb);
        JPanel khoaSVpn = new JPanel();
        khoaSVpn.setLayout(new FlowLayout(10, 10, 10));
        khoaSVpn.add(khoaSVlb);
        JPanel lopSVpn = new JPanel();
        lopSVpn.setLayout(new FlowLayout(10, 10 , 10));
        lopSVpn.add(lopSVlb);
        JPanel sDTSVpn = new JPanel();
        sDTSVpn.setLayout(new FlowLayout(10, 10, 10));
        sDTSVpn.add(sDTSVlb);
        sDTSVpn.add(sDTSVtf);
        JPanel emailSVpn = new JPanel();
        emailSVpn.setLayout(new FlowLayout(10, 10, 10));
        emailSVpn.add(emailSVlb);
        emailSVpn.add(emailSVtf);

        JPanel themSuaXoaSV1 = new JPanel();
        themSuaXoaSV1.setLayout(new GridLayout(6, 1));
        themSuaXoaSV1.add(maSVpn);
        themSuaXoaSV1.add(tenSVpn);
        themSuaXoaSV1.add(namSinhSVpn);
        themSuaXoaSV1.add(lopSVpn);
        themSuaXoaSV1.add(sDTSVpn);
        themSuaXoaSV1.add(emailSVpn);
        JPanel themSuaXoaSV2 = new JPanel();
        JButton thembtn = new JButton("Thêm");
        thembtn.setFont(mainFont);
        thembtn.setBounds(300, 50, 20, 50);
        JButton suabtn = new JButton("Sửa");
        suabtn.setFont(mainFont);
        JButton xoabtn = new JButton("Xóa");
        xoabtn.setFont(mainFont);
        JButton timKiembtn = new JButton("Tìm kiếm");
        timKiembtn.setFont(mainFont);
        themSuaXoaSV2.setLayout(new GridLayout(4, 1));
        themSuaXoaSV2.add(thembtn);
        themSuaXoaSV2.add(suabtn);
        themSuaXoaSV2.add(xoabtn);
        themSuaXoaSV2.add(timKiembtn);

        JPanel themSuaXoaSVtb1 = new JPanel(new BorderLayout());
        String columnThemSuaXoaSV[]={"STT","Mã số sinh viên","Họ tên sinh viên","Năm sinh","Khoa","Lớp","Số điện thoại","Email"};
        DefaultTableModel modelThemSuaXoaSV = new DefaultTableModel(columnThemSuaXoaSV, 0);
        JTable themSuaXoaSVtb = new JTable(modelThemSuaXoaSV);
        JScrollPane scrollPanethemSuaXoaSV = new JScrollPane(themSuaXoaSVtb);
        themSuaXoaSVtb1.add(scrollPanethemSuaXoaSV, BorderLayout.CENTER);

        JPanel themSuaXoaSV = new JPanel(new BorderLayout());
        themSuaXoaSV.add(themSuaXoaSV1, BorderLayout.WEST);
        themSuaXoaSV.add(themSuaXoaSV2, BorderLayout.EAST );
        themSuaXoaSV.add(themSuaXoaSVtb1, BorderLayout.SOUTH);

        JPanel hocPhipn = new JPanel(new BorderLayout());
        String columnHocPhiSV[]={"STT","Mã số Sinh viên","Họ tên sinh viên","Số tín chỉ","Tổng học phí"};
        DefaultTableModel modelHocPhiSV = new DefaultTableModel(columnHocPhiSV, 0);
        JTable hocPhiSV = new JTable(modelHocPhiSV);
        JScrollPane scrollPaneHocPhiSV = new JScrollPane(hocPhiSV);
        hocPhipn.add(scrollPaneHocPhiSV, BorderLayout.CENTER);

        JTabbedPane tabbedPane1 = new JTabbedPane();
        tabbedPane1.addTab("Danh sách sinh viên lớp: ", danhSachSV);
        tabbedPane1.addTab("Thêm, sửa, xóa, thông tin sinh viên", themSuaXoaSV);
        tabbedPane1.addTab("Học phí", hocPhipn);


        JPanel quanLySinhVienpn = new JPanel();
        quanLySinhVienpn.setLayout(new BorderLayout());
        JLabel quanLySinhVienlb = new JLabel("Quản lý sinh viên");
        quanLySinhVienlb.setFont(mainFont);
        quanLySinhVienpn.add(quanLySinhVienlb, BorderLayout.NORTH);
        quanLySinhVienpn.add(tabbedPane1, BorderLayout.CENTER);


        JPanel lichTrinhDayHocpn = new JPanel(new BorderLayout());
        JLabel lichTrinhDayHoclb = new JLabel("Lịch trình dạy học");
        lichTrinhDayHoclb.setFont(mainFont);
        lichTrinhDayHocpn.add(lichTrinhDayHoclb, BorderLayout.NORTH);
        String columnLichTrinhDayHoc[]={"STT","Tên giảng viên","Mã giảng viên","Môn giảng dạy","Mã phòng dạy","Thời gian dạy"};
        DefaultTableModel modelLichTrinhDayHoc = new DefaultTableModel(columnLichTrinhDayHoc, 0);
        JTable lichTrinhDayHoc = new JTable(modelLichTrinhDayHoc);
        JScrollPane scrollLichTrinhDayHoc = new JScrollPane(lichTrinhDayHoc);
        lichTrinhDayHocpn.add(scrollLichTrinhDayHoc, BorderLayout.CENTER);

        JPanel tenGiangVienpn = new JPanel(new FlowLayout(10, 30, 0));
        JLabel tenGiangVienlb = new JLabel("Tên giảng viên: ");
        tenGiangVienlb.setFont(miniFont);
        tenGiangVienpn.add(tenGiangVienlb);
        JTextField tenGiangVientf = new JTextField(25);
        tenGiangVientf.setFont(miniFont);
        tenGiangVienpn.add(tenGiangVientf);
        JPanel maGiangVienpn = new JPanel(new FlowLayout(10, 30, 0));
        JLabel maGiangVienlb = new JLabel("Mã Giảng viên: ");
        maGiangVienlb.setFont(miniFont);
        JTextField maGiangVientf = new JTextField(15);
        maGiangVientf.setFont(miniFont);
        maGiangVienpn.add(maGiangVienlb);
        maGiangVienpn.add(maGiangVientf);

        JPanel gioiTinhpn = new JPanel(new FlowLayout(10, 30, 0));
        JLabel gioiTinhlb = new JLabel("Giới tính");
        gioiTinhlb.setFont(miniFont);
        JTextField gioiTinhtf = new JTextField(10);
        gioiTinhtf.setFont(miniFont);
        gioiTinhpn.add(gioiTinhlb);
        gioiTinhpn.add(gioiTinhtf);
        JPanel chucVupn = new JPanel(new FlowLayout(10, 30, 0));
        JLabel chucVulb = new JLabel("Chức vụ: ");
        chucVulb.setFont(miniFont);
        JTextField chucVutf = new JTextField(20);
        chucVutf.setFont(miniFont);
        chucVupn.add(chucVulb);
        chucVupn.add(chucVutf);

        JPanel namSinhGiangVienpn = new JPanel(new FlowLayout(10, 30, 0));
        JLabel namSinhGiangVienlb = new JLabel("Năm sinh: ");
        namSinhGiangVienlb.setFont(miniFont);
        JTextField namSinhGiangVientf = new JTextField(10);
        namSinhGiangVientf.setFont(miniFont);
        namSinhGiangVienpn.add(namSinhGiangVienlb);
        namSinhGiangVienpn.add(namSinhGiangVientf);
        JPanel khoapn = new JPanel(new FlowLayout(10, 30, 0));
        JLabel khoalb = new JLabel("Khoa: ");
        khoalb.setFont(miniFont);
        JTextField khoatf = new JTextField(20);
        khoatf.setFont(miniFont);
        khoapn.add(khoalb);
        khoapn.add(khoatf);

        JPanel sDTGiangVienpn = new JPanel(new FlowLayout(10, 30, 0));
        JLabel sDTGiangVienlb = new JLabel("Số điện thoại: ");
        sDTGiangVienlb.setFont(miniFont);
        JTextField sDTGiangVientf = new JTextField(15);
        sDTGiangVientf.setFont(miniFont);
        sDTGiangVienpn.add(sDTGiangVienlb);
        sDTGiangVienpn.add(sDTGiangVientf);

        JPanel EmailGiangVienpn = new JPanel(new FlowLayout(10, 30, 0));
        JLabel emailGiangVienlb = new JLabel("Email: ");
        emailGiangVienlb.setFont(miniFont);
        JTextField emailGiangVientf = new JTextField(20);
        emailGiangVientf.setFont(miniFont);
        EmailGiangVienpn.add(emailGiangVienlb);
        EmailGiangVienpn.add(emailGiangVientf);

        JPanel mainTTGiangVienpn = new JPanel(new GridLayout(8, 1));
        mainTTGiangVienpn.add(tenGiangVienpn);
        mainTTGiangVienpn.add(maGiangVienpn);
        mainTTGiangVienpn.add(gioiTinhpn);
        mainTTGiangVienpn.add(khoapn);
        mainTTGiangVienpn.add(namSinhGiangVienpn);
        mainTTGiangVienpn.add(chucVupn);
        mainTTGiangVienpn.add(sDTGiangVienpn);
        mainTTGiangVienpn.add(EmailGiangVienpn);

        JPanel thayDoiTTCaNhanpn = new JPanel();
        JLabel thayDoiTTCaNhanlb = new JLabel("Thay đổi thông tin cá nhân");
        thayDoiTTCaNhanlb.setFont(miniFont);
        thayDoiTTCaNhanpn.add(thayDoiTTCaNhanlb);

        JPanel thongTinGiangVienpn = new JPanel(new BorderLayout());
        JLabel thongTinGiangVienlb = new JLabel("Thông tin giảng Viên");
        thongTinGiangVienlb.setFont(mainFont);
        thongTinGiangVienpn.add(thongTinGiangVienlb, BorderLayout.NORTH);
        thongTinGiangVienpn.add(mainTTGiangVienpn, BorderLayout.CENTER);
        thongTinGiangVienpn.add(thayDoiTTCaNhanpn, BorderLayout.SOUTH);

        JPanel dangXuatpn = new JPanel();
        JButton dangXuatbtn = new JButton("Đăng xuất");
        dangXuatbtn.setFont(miniFont);
        dangXuatpn.add(dangXuatbtn);
        dangXuatbtn.setBounds(0, 0, 20, 50);;

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Trang chủ", trangChupn);
        tabbedPane.addTab("Quản lý sinh viên", quanLySinhVienpn);
        tabbedPane.addTab("Lịch trình dạy học", lichTrinhDayHocpn);
        tabbedPane.addTab("Thông tin giảng viên", thongTinGiangVienpn);

        JPanel mainPN = new JPanel();
        mainPN.setLayout(new BorderLayout());
        mainPN.add(giangVienpn, BorderLayout.NORTH);
        mainPN.add(tabbedPane, BorderLayout.CENTER);
        mainPN.add(dangXuatbtn, BorderLayout.SOUTH);


        add(mainPN);
       

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        GiaoDienGiangVien gdGV = new GiaoDienGiangVien();
        gdGV.unit();
    }
}

