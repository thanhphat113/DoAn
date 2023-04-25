/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doan;

/**
 *
 * @author Admin
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GiaoDienQuanLy extends JFrame {

    private String userID;
    private String password;

    public GiaoDienQuanLy(String a, String b) {
        this.userID = a;
        this.password = b;
        unitGUI();
    }

    public void unitGUI() {
        setSize(900, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Ứng dụng quản lí sinh viên");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "title.png").getImage());

        Font font = new Font("Arial", Font.BOLD, 25);
        Font font1 = new Font("Arial", Font.BOLD, 15);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        JLabel tieuDe = new JLabel("Màn hình Quản Lý");
        tieuDe.setFont(font);

        JLabel xinchao = new JLabel("Xin chào " + "(" + userID + ")");
        JLabel DangXuat = new JLabel("<html><u>Đăng xuất</u></html>");
        DangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GiaoDienDNSinhVien();
                dispose();
            }
        });
        DangXuat.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel GioiThieu = new JPanel(new GridLayout(1, 2, 10, 0));
        GioiThieu.add(xinchao);
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

        //Trang Chủ==========================================================================================
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

        noiDung.add(NoiDung, BorderLayout.CENTER);
        JButton thongbao = new JButton("Thêm thông báo!", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "add_1.png"));
        thongbao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame f1 = new JFrame("Thay Đổi Nội Dung");
                f1.setVisible(true);
                f1.setSize(500, 500);
                f1.setLocationRelativeTo(null);
                f1.setLayout(new BorderLayout());
                f1.setIconImage(new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "update_1.png").getImage());

                JTextArea text = new JTextArea();
                text.setText(NoiDung.getText());

                JPanel nut = new JPanel(new GridLayout(1, 2));
                JButton btXacNhan = new JButton("Xác Nhận", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "yes_1.png"));
                btXacNhan.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        NoiDung.setText(text.getText());
                        try {
                            FileWriter writer = new FileWriter(".\\src\\doan\\NoiDung.txt");
                            writer.write(text.getText());
                            writer.close();
                        } catch (IOException evt) {
                            evt.printStackTrace();
                        }
                    }
                });
                JButton btHuy = new JButton("Hủy", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "no_1.png"));
                btHuy.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        f1.dispose();
                    }
                });

                nut.add(btXacNhan);
                nut.add(btHuy);
                f1.add(text, BorderLayout.CENTER);
                f1.add(nut, BorderLayout.SOUTH);

            }
        });

        trangChu.add(noiDung, BorderLayout.CENTER);
        trangChu.add(thongbao, BorderLayout.SOUTH);

        //Danh sách sinh viên==========================================================================================
        JPanel DSSV = new JPanel(new BorderLayout());
        JPanel Loc = new JPanel();
        Loc.setLayout(new BoxLayout(Loc, BoxLayout.X_AXIS));
        JLabel lb1 = new JLabel("Chọn lớp:");
        JComboBox jcb1 = new JComboBox();
        JButton bt1 = new JButton("Tìm kiếm");
        Loc.add(lb1);
        Loc.add(jcb1);
        Loc.add(bt1);
        JPanel chonLoc = new JPanel(new GridLayout(1, 2));
        chonLoc.add(Loc);
        chonLoc.setPreferredSize(new Dimension(0, 40));
        String columnDSSV[] = {"Mã SV", "Tên sinh viên", "Năm sinh", "Lớp", "Giới tính", "Dân tộc", "CCCD/CMND", "Số điện thoại"};
        DefaultTableModel modelDSSV = new DefaultTableModel(columnDSSV, 0);
        JTable bangDSSV = new JTable(modelDSSV);
        bangDSSV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSSV = new JScrollPane(bangDSSV);
        JPanel pn1 = new JPanel();
        JTextField msv = new JTextField();
        JLabel lbm = new JLabel("Mã sv: ");
        JTextField tensv = new JTextField();
        JLabel lbt = new JLabel("Tên: ");
        JTextField lop = new JTextField();
        JLabel lbl = new JLabel("Lớp");
        JTextField namsinh = new JTextField();
        JLabel lbn = new JLabel("Năm sinh");
        String[] gendersSV = {"Nam", "Nữ"};
        JComboBox<String> gioitinhSV = new JComboBox<>(gendersSV);
        JLabel lbg = new JLabel("Giới tính: ");
        JTextField dantoc = new JTextField();
        JLabel lbd = new JLabel("Dân tộc");
        JTextField cccd = new JTextField();
        JLabel lbc = new JLabel("CCCĐ/CMND");
        JTextField sdt = new JTextField();
        JLabel lbs = new JLabel("Số ĐT:");
        pn1.setLayout(new GridLayout(1, 4, 10, 10));
        pn1.add(lbm);
        pn1.add(msv);
        pn1.add(lbt);
        pn1.add(tensv);
        pn1.add(lbl);
        pn1.add(lop);
        pn1.add(lbn);
        pn1.add(namsinh);
        pn1.add(lbg);
        pn1.add(gioitinhSV);
        pn1.add(lbd);
        pn1.add(dantoc);
        pn1.add(lbc);
        pn1.add(cccd);
        pn1.add(lbs);
        pn1.add(sdt);
        pn1.setPreferredSize(new Dimension(0, 50));
        JPanel pn2 = new JPanel();
        pn2.setLayout(new GridLayout(1, 4, 10, 10));
        pn2.add(lbg);
        pn2.add(gioitinhSV);
        pn2.add(lbd);
        pn2.add(dantoc);
        pn2.add(lbc);
        pn2.add(cccd);
        pn2.add(lbs);
        pn2.add(sdt);
        pn2.setPreferredSize(new Dimension(0, 50));
        JPanel pn3 = new JPanel();
        JButton them = new JButton("Thêm");

        them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MaSV = msv.getText();
                String TenSV = tensv.getText();
                String Lop = lop.getText();
                String NamSinh = namsinh.getText();
                String GioiTinh = (String) gioitinhSV.getSelectedItem();
                String CCCD = cccd.getText();
                String Sdt = sdt.getText();
                String DanToc = dantoc.getText();

                Object[] rowData = {MaSV, TenSV, Lop, NamSinh, GioiTinh, DanToc, CCCD, Sdt};
                modelDSSV.addRow(rowData);
                bangDSSV.setModel(modelDSSV);

                msv.setText("");
                tensv.setText("");
                dantoc.setText("");
                lop.setText("");
                gioitinhSV.setSelectedIndex(0);
                cccd.setText("");
                sdt.setText("");
                dantoc.setText("");
                namsinh.setText("");
            }
        });
        JButton sua = new JButton("Sửa");
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        JButton xoa = new JButton("Xóa");
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bangDSSV.getSelectedRow();
                if (selectedRow != -1) {
                    modelDSSV.removeRow(selectedRow);
                }
            }
        });
        JButton xemdiem = new JButton("Xem điểm");
        JButton xemtkb = new JButton("Xem TKB");
        pn3.setLayout(new GridLayout(1, 5, 75, 50));
        pn3.add(them);
        pn3.add(sua);
        pn3.add(xoa);
        pn3.add(xemdiem);
        pn3.add(xemtkb);
        pn3.setPreferredSize(new Dimension(0, 55));
        JPanel pnp = new JPanel();
        pnp.setPreferredSize(new Dimension(0, 130));
        pnp.setLayout(new GridLayout(4, 1, 0, 10));
        pnp.add(pn1);
        pnp.add(pn2);
        pnp.add(pn3);
        pnp.add(chonLoc);

        pnp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSSV.add(scrollPaneDSSV, BorderLayout.CENTER);
        DSSV.add(pnp, BorderLayout.NORTH);

        //Danh sách giảng viên
        JPanel DSGV = new JPanel(new BorderLayout());
        JPanel locgv = new JPanel();
        locgv.setLayout(new BoxLayout(locgv, BoxLayout.X_AXIS));
        JLabel lbgv = new JLabel("Nhập mã giảng viên: ");
        JTextField jcbgv = new JTextField();
        JButton btlocgv = new JButton("Tìm kiếm");
        locgv.add(lbgv);
        locgv.add(jcbgv);
        locgv.add(btlocgv);
        JPanel chonlocgv = new JPanel(new GridLayout(1, 2));
        chonlocgv.add(locgv);
        chonlocgv.setPreferredSize(new Dimension(0, 40));
        String columnDSGV[] = {"Mã GV", "Tên giảng viên", "Trình độ", "Số điện thoại", "Email"};
        DefaultTableModel modelDSGV = new DefaultTableModel(columnDSGV, 0);
        JTable bangDSGV = new JTable(modelDSGV);
        bangDSGV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSGV = new JScrollPane(bangDSGV);
        JPanel pn4 = new JPanel();
        JTextField mGV = new JTextField();
        JLabel lbmg = new JLabel("Mã GV: ");
        JTextField tenGV = new JTextField();
        JLabel lbtg = new JLabel("Tên giảng viên: ");
        String[] gendersGV = {"Nam", "Nữ"};
        JComboBox<String> gioitinhGV = new JComboBox<>(gendersGV);
        JLabel lbgg = new JLabel("Giới tính: ");
        JTextField trinhdo = new JTextField();
        JLabel lbtd = new JLabel("Trình độ ");
        JTextField sdtgv = new JTextField();
        JLabel lbsg = new JLabel("Số ĐT:");
        JTextField email = new JTextField();
        JLabel lbe = new JLabel("Email:");
        pn4.setLayout(new GridLayout(1, 4, 10, 10));
        pn4.add(lbmg);
        pn4.add(mGV);
        pn4.add(lbtg);
        pn4.add(tenGV);
        pn4.add(lbgg);
        pn4.add(gioitinhGV);
        pn4.setPreferredSize(new Dimension(0, 50));
        JPanel pn5 = new JPanel();
        pn5.setLayout(new GridLayout(1, 4, 10, 10));
        pn5.add(lbtd);
        pn5.add(trinhdo);
        pn5.add(lbsg);
        pn5.add(sdtgv);
        pn5.add(lbe);
        pn5.add(email);
        pn5.setPreferredSize(new Dimension(0, 50));
        JPanel pn6 = new JPanel();
        JButton themg = new JButton("Thêm");
        JButton suag = new JButton("Sửa");
        JButton xoag = new JButton("Xóa");
        JButton xemlichgiang = new JButton("Xem lịch giảng");
        pn6.setLayout(new GridLayout(1, 4, 75, 50));
        pn6.add(themg);
        pn6.add(suag);
        pn6.add(xoag);
        pn6.add(xemlichgiang);
        pn6.setPreferredSize(new Dimension(0, 55));
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(0, 130));
        pn.setLayout(new GridLayout(4, 1, 0, 10));
        pn.add(pn4);
        pn.add(pn5);
        pn.add(pn6);
        pn.add(chonlocgv);
        pn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSGV.add(scrollPaneDSGV, BorderLayout.CENTER);
        DSGV.add(pn, BorderLayout.NORTH);

        //DSMH
        JPanel DSMH = new JPanel(new BorderLayout());
        JLabel MH = new JLabel("Danh sách Môn học");
        MH.setAlignmentX(Component.CENTER_ALIGNMENT);
        MH.setAlignmentY(Component.CENTER_ALIGNMENT);
        MH.setFont(font1);
        JPanel titlePnMH = new JPanel();
        titlePnMH.setLayout(new BoxLayout(titlePnMH, BoxLayout.Y_AXIS));
        titlePnMH.add(Box.createVerticalGlue());
        titlePnMH.add(MH);
        titlePnMH.add(Box.createVerticalGlue());
        titlePnMH.setPreferredSize(new Dimension(0, 40));
        String columnDSMH[] = {"Mã Học Phần", "Tên Học Phần", "Tiết Bắt Đầu", "Số Tiết", "Giảng Viên", "Ngày Bắt Đầu"};
        DefaultTableModel modelDSMH = new DefaultTableModel(columnDSMH, 0);
        JTable tableDSMH = new JTable(modelDSMH);
        tableDSMH.getTableHeader().setReorderingAllowed(false);
        Border border2 = BorderFactory.createLineBorder(Color.BLACK);

        tableDSMH.setBorder(border2);
        JPanel pnDSMH = new JPanel();
        pnDSMH.setPreferredSize(new Dimension(0, 25));

        DSMH.add(new JScrollPane(tableDSMH), BorderLayout.CENTER);
        DSMH.add(titlePnMH, BorderLayout.NORTH);
        DSMH.add(pnDSMH, BorderLayout.SOUTH);

        //Tình trạng học phí
        JPanel TTHP = new JPanel(new BorderLayout());
        JLabel HP = new JLabel("Tình trạng Học phí");
        HP.setAlignmentX(Component.CENTER_ALIGNMENT);
        HP.setAlignmentY(Component.CENTER_ALIGNMENT);
        HP.setFont(font1);
        JPanel titlePnTTHP = new JPanel();
        titlePnTTHP.setLayout(new BoxLayout(titlePnTTHP, BoxLayout.Y_AXIS));
        titlePnTTHP.add(Box.createVerticalGlue());
        titlePnTTHP.add(HP);
        titlePnTTHP.add(Box.createVerticalGlue());
        titlePnTTHP.setPreferredSize(new Dimension(0, 40));
        String columnTTHP[] = {"Mã sinh viên", "Tên sinh viên", "Lớp", "Học kỳ", "Năm học", "Học phí", "Trường hợp miễn giảm", "Tổng", "Tình trạng"};
        DefaultTableModel modelTTHP = new DefaultTableModel(columnTTHP, 0);
        JTable tableTTHP = new JTable(modelTTHP);
        tableTTHP.getTableHeader().setReorderingAllowed(false);

        tableTTHP.setBorder(border);
        JPanel pnTTHP = new JPanel();
        pnTTHP.setPreferredSize(new Dimension(0, 25));

        TTHP.add(new JScrollPane(tableTTHP), BorderLayout.CENTER);
        TTHP.add(titlePnTTHP, BorderLayout.NORTH);
        TTHP.add(pnTTHP, BorderLayout.SOUTH);

        // Thêm Vô tabbedPane
        JTabbedPane thanhChon = new JTabbedPane();
        thanhChon.addTab("Trang Chủ", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "home_1.png"), trangChu);
        thanhChon.addTab("Danh sách SV", DSSV);
        thanhChon.addTab("Danh sách GV", DSGV);
        thanhChon.addTab("Danh sách MH", DSMH);
        thanhChon.addTab("Học phí", TTHP);

        //Thêm Vào Jframe
        add(titlePn, BorderLayout.NORTH);
        add(thanhChon, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.SOUTH);

    }
}