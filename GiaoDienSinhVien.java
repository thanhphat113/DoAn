package DoAn;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GiaoDienSinhVien extends JFrame {
    public GiaoDienSinhVien(){
        unitGUI();
    }

    public void unitGUI(){
        setSize(800,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Ứng Dụng Quản Lý Sinh Viên");
        setLayout(new BorderLayout());

        Font font = new Font("Arial", Font.BOLD, 25);
		JLabel tieuDe=new JLabel("Màn Hình Sinh Viên");
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
		tieuDe.setFont(font);
		JPanel titlePn=new JPanel();
		titlePn.setLayout(new BoxLayout(titlePn, BoxLayout.Y_AXIS));
		titlePn.add(Box.createVerticalGlue());
		titlePn.add(tieuDe);
        titlePn.add(GioiThieu);
		titlePn.add(Box.createVerticalGlue());
        titlePn.setPreferredSize(new Dimension(0,50));
        titlePn.setBackground(new Color(213,232,212));
        titlePn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JTabbedPane thanhChon = new JTabbedPane();
        
        // Xem Điểm-center
        JPanel xemDiem = new JPanel(new BorderLayout());
        String columnXemDiem[]={"Mã Học Phần","Tên Học Phần","Số Tín Chỉ","Điểm Giữa Kỳ","Điểm Cuối Kì","Kết Quả"};
        DefaultTableModel modelXemDiem = new DefaultTableModel(columnXemDiem, 0);
        JTable bangDiem=new JTable(modelXemDiem);
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
        JScrollPane scrollPaneDKMH = new JScrollPane(bangDKMH);
        DKMH.add(scrollPaneDKMH,BorderLayout.CENTER);
        DKMH.add(chonLoc,BorderLayout.NORTH);
        
        //Xem TKB
        JPanel xemTKB=new JPanel(new BorderLayout());
        // String Thu[]= {"Thứ 2","Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7"}; 
        // DefaultTableModel modelXemTKB = new DefaultTableModel(Thu, 10);
        // JTable TKB=new JTable(modelXemTKB);
        // for (int i=0;i<10;i++){
        //     TKB.setValueAt("",
        // }
        // xemTKB.add(TKB,BorderLayout.CENTER);

        
        // Thêm Vô tabbedPane
        thanhChon.addTab("Xem Điểm", xemDiem);
        thanhChon.addTab("TKB", xemTKB);
        thanhChon.addTab("ĐKMH", DKMH);

        
        
        //Thêm Vào Jframe
        add(titlePn,BorderLayout.NORTH);
        add(thanhChon,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
        add(new JPanel(),BorderLayout.SOUTH);
    }


    public static void main(String[] args){
        new GiaoDienSinhVien();
    }
}