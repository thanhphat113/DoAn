package DoAn;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GiaoDienDNSinhVien extends JFrame implements ActionListener {
	public GiaoDienDNSinhVien(){
		unitGUI();
	}

	JRadioButton radio1;
	private JRadioButton radio2,radio3;

	public void unitGUI(){
		setSize(500,300);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Ứng Dụng Quản Lý Sinh Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							

									// Màn Hình Sinh Viên
		//Top---Màn Hình Đăng Nhập
		Font font = new Font("Arial", Font.BOLD, 25);
		JLabel tieuDe=new JLabel("Màn Hình Đăng Nhập");
		tieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
		tieuDe.setAlignmentY(Component.CENTER_ALIGNMENT);
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
		JTextField khungDN=new JTextField();
		khungDN.setPreferredSize(new Dimension(0,0));
		JLabel MatKhau=new JLabel("Mật Khẩu:");
		JTextField khungMK=new JTextField();
		khungMK.setPreferredSize(new Dimension(0,0));
		DangNhap.add(luaChon);
		DangNhap.add(tenDN);
		DangNhap.add(khungDN);
		DangNhap.add(MatKhau);
		DangNhap.add(khungMK);
		
		JLabel pnlRONG1=new JLabel();
		pnlRONG1.setPreferredSize(new Dimension(100,0));
		JLabel pnlRONG2=new JLabel();
		pnlRONG2.setPreferredSize(new Dimension(100,0));

		//Bot ------- Màn Hình Đăng Nhập
		JButton btDangNhap= new JButton("Đăng Nhập");
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
		btDangNhap.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
			
		if (radio1.isSelected()) {
			new GiaoDienSinhVien();
		} else if (radio2.isSelected()) {
			System.out.println("Giảng Viên");
		} else if (radio3.isSelected()) {
			System.out.println("Khoa");
		}
		dispose();
}


	public static void main(String[] args){
		new GiaoDienDNSinhVien();
	}
}
