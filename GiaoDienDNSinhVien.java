package doan;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GiaoDienDNSinhVien extends JFrame {

    public GiaoDienDNSinhVien() {
        unitGUI();
    }

    private JTextField khungDN;
    private JPasswordField khungMK;
    private JRadioButton radio1, radio2, radio3;

    //đường link dẫn tới tệp icon
    private static String pathIcon = ".\\src\\doan\\icon\\";

    public void unitGUI() {
        setSize(500, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setTitle("Ứng Dụng Quản Lý Sinh Viên");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(pathIcon + "title.png").getImage());

        // Màn Hình Sinh Viên
        //Top---Màn Hình Đăng Nhập
    
        JLabel tieuDe = new JLabel("Màn Hình Đăng Nhập", new ImageIcon(pathIcon + "account_1.png"), JLabel.CENTER);
        tieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
        tieuDe.setAlignmentY(Component.CENTER_ALIGNMENT);
        Font font = new Font("Arial", Font.BOLD, 25);
        tieuDe.setFont(font);
        JPanel titlePn = new JPanel();
        titlePn.setLayout(new BoxLayout(titlePn, BoxLayout.Y_AXIS));
        titlePn.add(Box.createVerticalGlue());
        titlePn.add(tieuDe);
        titlePn.add(Box.createVerticalGlue());
        titlePn.setPreferredSize(new Dimension(0, 60));
        titlePn.setBackground(new Color(213, 232, 212));
        titlePn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //Center----Màn Hình Đăng Nhập
        //Lựa Chọn
        radio1 = new JRadioButton("Sinh Viên");
        radio2 = new JRadioButton("Giảng Viên");
        radio3 = new JRadioButton("Quản Lý");
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        JPanel luaChon = new JPanel();
        luaChon.setLayout(new FlowLayout());
        luaChon.add(radio1);
        luaChon.add(radio2);
        luaChon.add(radio3);
        luaChon.setPreferredSize(new Dimension(0, 30));

        //Khung Đăng Nhập
        JPanel DangNhap = new JPanel();
        DangNhap.setLayout(new GridLayout(5, 1));
        JPanel pnKhungDN=new JPanel(new FlowLayout());
        pnKhungDN.add(new JLabel());
        JLabel tenDN = new JLabel("Tên Đăng Nhập:");
        khungDN = new JTextField();
        khungDN.setPreferredSize(new Dimension(200,30));
        pnKhungDN.add(khungDN);
        JPanel pnKhungMK=new JPanel(new FlowLayout());
        pnKhungMK.setPreferredSize(new Dimension(200,40));
        pnKhungMK.add(new JLabel());
        JLabel MatKhau = new JLabel("Mật Khẩu:");
        khungMK = new JPasswordField();
        khungMK.setPreferredSize(new Dimension(200,30));
        pnKhungMK.add(khungMK);
        DangNhap.add(luaChon);
        DangNhap.add(tenDN);
        DangNhap.add(pnKhungDN);
        DangNhap.add(MatKhau);
        DangNhap.add(pnKhungMK);
        JPanel dangNhap = new JPanel();
        dangNhap.setLayout(new BoxLayout(dangNhap, BoxLayout.Y_AXIS));
        
        dangNhap.add(DangNhap);
        
//
        JLabel pnlRONG1 = new JLabel();
        pnlRONG1.setPreferredSize(new Dimension(80, 0));
        JLabel pnlRONG2 = new JLabel();
        pnlRONG2.setPreferredSize(new Dimension(80, 0));

        //Bot ------- Màn Hình Đăng Nhập
        JButton btDangNhap = new JButton("Đăng Nhập", new ImageIcon(pathIcon + "log_in_1.png"));
        btDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String TK = khungDN.getText();
                char[] MKhau = khungMK.getPassword();
                String MK = new String(MKhau);
                if (radio1.isSelected()) {
                    if (TK.isEmpty() || MK.isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Tài khoản hoặc mật khẩu của bạn không hợp lệ!!!");
                    } else {
                        GiaoDienSinhVien frGDSV = new GiaoDienSinhVien(TK, MK);
                        frGDSV.setVisible(true);
                        dispose();
                    }
                } else if (radio2.isSelected()) {
                    if (TK.isEmpty() || MK.isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Tài khoản hoặc mật khẩu của bạn không hợp lệ!!!");
                    } else {
                        GiaoDienGiangVien frGDGV = new GiaoDienGiangVien(TK, MK);
                        frGDGV.setVisible(true);
                        dispose();
                    }
                } else if (radio3.isSelected()) {
                    if (TK.isEmpty() || MK.isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Tài khoản hoặc mật khẩu của bạn không hợp lệ!!!");
                    } else {
                        GiaoDienQuanLy frGDK = new GiaoDienQuanLy(TK, MK);
                        frGDK.setVisible(true);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Vui Lòng Chọn Loại Đối Tượng");
                }
            }
        });

        JPanel btBot = new JPanel(new FlowLayout());
        btBot.add(btDangNhap);
        btBot.setPreferredSize(new Dimension(0, 50));

        add(titlePn, BorderLayout.NORTH);
        add(dangNhap, BorderLayout.CENTER);
        add(pnlRONG1, BorderLayout.EAST);
        add(pnlRONG2, BorderLayout.WEST);
        add(btBot, BorderLayout.SOUTH);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
            Component c = (Component)evt.getSource();
            dangNhap.setPreferredSize(c.getSize());
            
        }
});
    }

    public static String getPathIcon() {
        return pathIcon;
    }
}