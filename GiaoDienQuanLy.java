package GUI;



import DAO.GiangVienDAO;
import DAO.PhongHocDAO;
import DAO.SinhVienDAO;
import DTO.GiangVien;
import DTO.PhongHoc;
import DTO.SinhVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class GiaoDienQuanLy extends JFrame {
    
    private String userID;
    private String password;

    public GiaoDienQuanLy() {
        unitGUI();
    }

    
    public GiaoDienQuanLy(String a, String b) {
        this.userID = a;
        this.password = b;
        unitGUI();
    }

    public void unitGUI() {
        setSize(1150, 700);
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
            FileReader fileReader = new FileReader("./NoiDung.txt");
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
                            FileWriter writer = new FileWriter("./NoiDung.txt");
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

        //Danh sách sinh viên=======================================================================================
        JPanel DSSV = new JPanel(new BorderLayout());
        JPanel Loc = new JPanel();
        Loc.setLayout(new BoxLayout(Loc, BoxLayout.X_AXIS));
        
        String columnDSSV[] = {"Mã SV", "Tên Sinh Viên", "Năm sinh", "Giới tính", "Dân tộc", "CCCD/CMND", "Số điện thoại","Tên Lớp","Tên Khoa","Tên Cố Vấn"};
        DefaultTableModel modelDSSV = new DefaultTableModel(columnDSSV, 0);
        
        ArrayList<SinhVien> svList = SinhVienDAO.selectAll();
        for (SinhVien sv : svList) {
            Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(),sv.getDanToc(),sv.getCCCD(),sv.getSoDienThoai(),sv.getTenLop(),sv.getTenKhoa(),sv.getTenCoVan()};
            modelDSSV.addRow(duLieu);
        }
        JTable bangDSSV = new JTable(modelDSSV);
        bangDSSV.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModel = bangDSSV.getColumnModel();
        
        //Kich Thước Cột
        columnModel.getColumn(0).setPreferredWidth(30); 
        columnModel.getColumn(1).setPreferredWidth(130);
        columnModel.getColumn(3).setPreferredWidth(10);
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(20);
        columnModel.getColumn(9).setPreferredWidth(130);
        
        bangDSSV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSSV = new JScrollPane(bangDSSV);
        
        
        JTextField jcb1 = new JTextField();
        jcb1.setEnabled(false);
        String[] TIMKIEM = {"Tất Cả","Mã Sinh Viên","Tên Sinh Viên", "Năm Sinh","Giới Tính","Dân Tộc","CCCD","Số Điện Thoại","Tên Lớp","Tên Khoa","Tên Cố Vấn"};
        JComboBox<String> DULIEU = new JComboBox<>(TIMKIEM);
        DULIEU.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DULIEU.getSelectedIndex() == 0) {
                // Nếu chọn phần tử đầu tiên thì không cho phép chỉnh sửa trường văn bản
                jcb1.setEnabled(false);
            } else {
                // Nếu chọn bất kỳ phần tử nào khác thì cho phép chỉnh sửa trường văn bản
                jcb1.setEnabled(true);
            }
        }
    });

        
     
        JButton bt1 = new JButton("Lọc >>");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag=false;
                String luaChonTimKiem = (String) DULIEU.getSelectedItem();                     
                String noiDung=new String();
                if(luaChonTimKiem.equalsIgnoreCase("Tất Cả")){
                    DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                    model.setRowCount(0);
                    ArrayList<SinhVien> svList = SinhVienDAO.selectAll();
                    for (SinhVien sv : svList) {
                        Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(),sv.getDanToc(),sv.getCCCD(),sv.getSoDienThoai(),sv.getTenLop(),sv.getTenKhoa(),sv.getTenCoVan()};
                        modelDSSV.addRow(duLieu);
                        flag=true;
                    }
                }
                else if(luaChonTimKiem.equalsIgnoreCase("Tên Sinh Viên"))    noiDung="sv.tenSinhVien";
                else if(luaChonTimKiem.equalsIgnoreCase("Mã Sinh Viên")) noiDung="sv.maSinhvien";
                else if(luaChonTimKiem.equalsIgnoreCase("Dân Tộc")) noiDung="sv.danToc";
                else if(luaChonTimKiem.equalsIgnoreCase("Giới Tính")) noiDung="sv.gioiTinh";
                else if(luaChonTimKiem.equalsIgnoreCase("Năm Sinh")) noiDung="sv.namSinh";
                else if(luaChonTimKiem.equalsIgnoreCase("CCCD")) noiDung="sv.CCCD";
                else if(luaChonTimKiem.equalsIgnoreCase("Số Điện Thoại")) noiDung="sv.soDienThoai";
                else if(luaChonTimKiem.equalsIgnoreCase("Tên Khoa")) noiDung="k.tenKhoa";
                else if(luaChonTimKiem.equalsIgnoreCase("Tên Lớp")) noiDung="l.tenLop";
                else  noiDung="gv.tenGiangVien";
                if(!flag){
                ArrayList<SinhVien> list=SinhVienDAO.selectByCondition(noiDung, jcb1.getText());
                DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                model.setRowCount(0);
                for (SinhVien sv : list) {
                    Object[] duLieuTimKiem = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(),sv.getDanToc(),sv.getCCCD(),sv.getSoDienThoai(),sv.getTenLop(),sv.getTenKhoa(),sv.getTenCoVan()};
                    modelDSSV.addRow(duLieuTimKiem);
                    jcb1.setText("");
                    }
                }
            }
        });
        Loc.add(DULIEU);
        Loc.add(jcb1);
        Loc.add(bt1);
        JPanel chonLoc = new JPanel(new GridLayout(1,2));
        chonLoc.add(Loc);
        chonLoc.add(new JPanel());
        
        JPanel pn1 = new JPanel();
        
        
        //Năm Sinh
        UtilDateModel dateModel = new UtilDateModel();
        dateModel.setDate(2000, 0, 1); // Đặt giá trị ban đầu cho model
        dateModel.setSelected(true); // Chọn giá trị ban đầu
        
        // Tạo DatePicker
        JDatePicker namsinh = new JDateComponentFactory().createJDatePicker(dateModel);
        namsinh.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        namsinh.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        namsinh.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel ngaysinh=new JPanel(new BorderLayout());
        ngaysinh.add((Component) namsinh,BorderLayout.CENTER);
        ngaysinh.setPreferredSize(new Dimension(200, 30));

        JTextField msv = new JTextField();
        msv.setPreferredSize(new Dimension(130, 30));
        JLabel lbm = new JLabel("Mã sv: ");
        JTextField tensv = new JTextField();
        tensv.setPreferredSize(new Dimension(200, 30));
        JLabel lbt = new JLabel("Tên: ");
        JTextField lop = new JTextField();
        lop.setPreferredSize(new Dimension(70, 30));
        JLabel lbl = new JLabel("Mã Lớp");
        JLabel lbk = new JLabel("Mã Khoa");
        JTextField khoa = new JTextField();
        khoa.setPreferredSize(new Dimension(70, 30));
        JLabel lbn = new JLabel("Năm sinh");
        String[] genders = {"Nam", "Nữ"};
        JComboBox<String> gioitinh = new JComboBox<>(genders);
        JLabel lbg = new JLabel("Giới tính: ");
        JTextField dantoc = new JTextField();
        dantoc.setPreferredSize(new Dimension(60, 30));
        JLabel lbd = new JLabel("Dân tộc");
        JTextField cccd = new JTextField();
        cccd.setPreferredSize(new Dimension(180, 30));
        JLabel lbc = new JLabel("CCCĐ/CMND");
        JTextField sdt = new JTextField();
        sdt.setPreferredSize(new Dimension(130, 30));
        JLabel lbs = new JLabel("Số ĐT:");
        pn1.setLayout(new FlowLayout());
        pn1.add(lbm);
        pn1.add(msv);
        pn1.add(lbt);
        pn1.add(tensv);
        pn1.add(lbg);
        pn1.add(gioitinh);
        pn1.add(lbl);
        pn1.add(lop);
        pn1.add(lbk);
        pn1.add(khoa);
        pn1.setPreferredSize(new Dimension(0, 50));
        
        
        JPanel pn2 = new JPanel();
        pn2.setLayout(new FlowLayout());
        pn2.add(lbn);
        pn2.add(ngaysinh);
        pn2.add(lbd);
        pn2.add(dantoc);
        pn2.add(lbc);
        pn2.add(cccd);
        pn2.add(lbs);
        pn2.add(sdt);
       
        JPanel pn3 = new JPanel();
        
        JButton them = new JButton("Thêm");
        them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MaSV = msv.getText();
                String TenSV = tensv.getText();
                String Lop = lop.getText();

                Date dateOfBirth = (Date) namsinh.getModel().getValue();
                // Định dạng lại định dạng ngày tháng năm
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(dateOfBirth);
                
                String GioiTinh = (String) gioitinh.getSelectedItem();
                String CCCD = cccd.getText();
                String Sdt = sdt.getText();
                String DanToc = dantoc.getText();
                String maKhoa=khoa.getText();
                SinhVien sv=new SinhVien(MaSV,TenSV,dateString,GioiTinh,DanToc,CCCD,Sdt,maKhoa,Lop);
                if(SinhVienDAO.insert(sv)){
                DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                model.setRowCount(0);
                ArrayList<SinhVien> list=SinhVienDAO.selectAll();
                for (SinhVien svThem : list) {
                    Object[] duLieuTimKiem = {svThem.getMaSinhVien(), svThem.getTenSinhVien(), svThem.getNamSinh(), svThem.getGioiTinh(),svThem.getDanToc(),svThem.getCCCD(),svThem.getSoDienThoai(),svThem.getTenLop(),svThem.getTenKhoa(),svThem.getTenCoVan()};
                    modelDSSV.addRow(duLieuTimKiem);
                }
                    
                    msv.setText("");
                    tensv.setText("");
                    dantoc.setText("");
                    lop.setText("");
                    gioitinh.setSelectedIndex(0);
                    cccd.setText("");
                    sdt.setText("");
                    dantoc.setText("");
                    khoa.setText("");
                }
                else JOptionPane.showMessageDialog(new JFrame(), "Thêm Không Thành Công");
                
            }
        });
        
        
        JButton sua = new JButton("Sửa");
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bangDSSV.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một dòng để thực hiện tác vụ");
                    return;
                }
                else{
                JFrame btSua=new JFrame ("Sửa Đổi Thông Tin");
                btSua.setSize(500,500);
                btSua.setVisible(true);
                btSua.setLocationRelativeTo(null);
                btSua.setLayout(new BorderLayout());
                
                TableModel model = bangDSSV.getModel();
                Object value= new Object();

                value = model.getValueAt(selectedRow, 0);
                String SVSua = value.toString();

                
                SinhVien sv= new SinhVien();
                sv=SinhVienDAO.selectById(SVSua);
                JPanel pnTTCN= new JPanel();
                pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
                JPanel pnTTCN1 = new JPanel(new GridLayout(9, 1));

                //CENTER-MaSV
                JPanel MaSV = new JPanel(new FlowLayout());
                JLabel maSv = new JLabel("Mã Sinh Viên");
                maSv.setPreferredSize(new Dimension(100, 30));
                JLabel tfTTCN1 = new JLabel();
                tfTTCN1.setPreferredSize(new Dimension(200, 30));
                tfTTCN1.setText(sv.getMaSinhVien());
                MaSV.add(maSv);
                MaSV.add(tfTTCN1);
                pnTTCN1.add(MaSV);

                //CENTER-TenSV
                JPanel TenSV = new JPanel(new FlowLayout());
                JLabel TenSv = new JLabel("Họ Và Tên");
                TenSv.setPreferredSize(new Dimension(100, 30));
                TenSv.setSize(100, 100);
                JTextField tfTTCN2 = new JTextField();
                tfTTCN2.setText(sv.getTenSinhVien());
                tfTTCN2.setPreferredSize(new Dimension(200, 30));
                TenSV.add(TenSv);
                TenSV.add(tfTTCN2);
                pnTTCN1.add(TenSV);

                //CENTER-Nam Sinh
                JPanel NamSinh = new JPanel(new FlowLayout());
                JLabel namSinh = new JLabel("Năm Sinh");
                namSinh.setPreferredSize(new Dimension(100, 30));
                namSinh.setSize(100, 100);
                JTextField tfTTCN3 = new JTextField();
                tfTTCN3.setText(sv.getNamSinh());
                tfTTCN3.setPreferredSize(new Dimension(200, 30));
                NamSinh.add(namSinh);
                NamSinh.add(tfTTCN3);
                pnTTCN1.add(NamSinh);

                //CENTER-Giới Tính
                 JPanel GioiTinh = new JPanel(new FlowLayout());
                JLabel gioitinh = new JLabel("Giới Tính");
                String[] luaChonTTCN = {"Nam", "Nữ"};
                JComboBox<String> LuaChonTTCN = new JComboBox<>(luaChonTTCN);
                if(sv.getGioiTinh().equalsIgnoreCase("Nam")){
                    LuaChonTTCN.setSelectedIndex(0);
                }
                else LuaChonTTCN.setSelectedIndex(1);
                gioitinh.setPreferredSize(new Dimension(100, 30));
                LuaChonTTCN.setPreferredSize(new Dimension(200, 30));
                GioiTinh.add(gioitinh);
                GioiTinh.add(LuaChonTTCN);
                pnTTCN1.add(GioiTinh);

                //CENTER-Dân Tộc
                JPanel DanToc = new JPanel(new FlowLayout());
                JLabel dantoc = new JLabel("Dân Tộc");
                dantoc.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN5 = new JTextField();
                tfTTCN5.setText(sv.getDanToc());
                tfTTCN5.setPreferredSize(new Dimension(200, 30));
                DanToc.add(dantoc);
                DanToc.add(tfTTCN5);
                pnTTCN1.add(DanToc);

                //CENTER-CCCD
                JPanel CCCD = new JPanel(new FlowLayout());
                JLabel cccd = new JLabel("CCCD/CMND");
                cccd.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN6 = new JTextField();
                tfTTCN6.setText(sv.getCCCD());
                tfTTCN6.setPreferredSize(new Dimension(200, 30));
                CCCD.add(cccd);
                CCCD.add(tfTTCN6);
                pnTTCN1.add(CCCD);

                //CENTER-Sđt
                JPanel SDT = new JPanel(new FlowLayout());
                JLabel sdt = new JLabel("Số Điện Thoại");
                sdt.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN7 = new JTextField();
                tfTTCN7.setText(sv.getSoDienThoai());
                tfTTCN7.setPreferredSize(new Dimension(200, 30));
                SDT.add(sdt);
                SDT.add(tfTTCN7);
                pnTTCN1.add(SDT);

                //Mã Khoa
                JPanel KHOA = new JPanel(new FlowLayout());
                JLabel khoa = new JLabel("Mã Khoa");
                khoa.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN8 = new JTextField();
                tfTTCN8.setText(sv.getMaKhoa());
                tfTTCN8.setPreferredSize(new Dimension(200, 30));
                KHOA.add(khoa);
                KHOA.add(tfTTCN8);
                pnTTCN1.add(KHOA);

                //Mã Lớp
                JPanel LOP = new JPanel(new FlowLayout());
                JLabel lop = new JLabel("Mã Lớp");
                lop.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN9 = new JTextField();
                tfTTCN9.setText(sv.getMaLop());
                tfTTCN9.setPreferredSize(new Dimension(200, 30));
                LOP.add(lop);
                LOP.add(tfTTCN9);
                pnTTCN1.add(LOP);

                pnTTCN.add(pnTTCN1);
                btSua.add(pnTTCN,BorderLayout.CENTER);
                
                JPanel btXacNhan=new JPanel(new FlowLayout());
                JButton XacNhan=new JButton("Xác Nhận");
                XacNhan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String gioiTinh = (String) LuaChonTTCN.getSelectedItem();
                        String TenSV=tfTTCN2.getText();
                        String namSinh=tfTTCN3.getText();             
                        String danToc=tfTTCN5.getText();
                        String CCCD=tfTTCN6.getText();
                        String sdt=tfTTCN7.getText();
                        String maKhoa=tfTTCN8.getText();
                        String maLop=tfTTCN9.getText();
                        SinhVien sv=new SinhVien(SVSua,TenSV,namSinh,gioiTinh,danToc,CCCD,sdt,maKhoa,maLop);
                        if(SinhVienDAO.update(sv)&&TenSV!=null&&namSinh!=null&&danToc!=null&&CCCD!=null&&sdt!=null&&maKhoa!=null&&maLop!=null){
                            JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Thành Công");
                            SinhVien update=new SinhVien();
                            DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                            model.setRowCount(0);
                            ArrayList<SinhVien> list=SinhVienDAO.selectAll();
                            for (SinhVien svup : list) {
                                Object[] duLieuTimKiem = {svup.getMaSinhVien(), svup.getTenSinhVien(), svup.getNamSinh(), svup.getGioiTinh(),svup.getDanToc(),svup.getCCCD(),svup.getSoDienThoai(),svup.getTenLop(),svup.getTenKhoa(),svup.getTenCoVan()};
                                modelDSSV.addRow(duLieuTimKiem);
                            }
                            btSua.dispose();
                        }
                        else JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Không Thành Công");
                    } 
            });
                JButton Huy=new JButton("Hủy");
                Huy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            btSua.dispose();
                        } 
            });
                btXacNhan.add(XacNhan);
                btXacNhan.add(Huy);
                btSua.add(btXacNhan,BorderLayout.SOUTH);
            
            }
            }
        });

        JButton xoa = new JButton("Xóa");
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel model = bangDSSV.getModel();
                int selectedRow = bangDSSV.getSelectedRow();
                Object value = model.getValueAt(selectedRow, 0); // Lấy giá trị của ô đầu tiên trong dòng đã chọn
                // Lưu giá trị vào biến
                String MaSVXoa = value.toString();
                
                if (SinhVienDAO.delete(MaSVXoa)){
                    JOptionPane.showMessageDialog(new JFrame(), "Đã Xóa Thành Công");
                    if (selectedRow != -1) {
                    modelDSSV.removeRow(selectedRow);
                }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "Không Tìm Thấy Sinh Viên Cần Xóa");
            }
        });
        JButton timKiem = new JButton("Tìm Kiếm");
        timKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame btTimKiem=new JFrame("Tìm Kiếm Nâng Cao");
                btTimKiem.setSize(400,700);
                btTimKiem.setVisible(true);
                btTimKiem.setLocationRelativeTo(null);


                JPanel pnTTCN= new JPanel();
                pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
                JPanel pnTTCN1 = new JPanel(new GridLayout(10, 1));

                //CENTER-MaSV
                JPanel MaSV = new JPanel(new FlowLayout());
                JLabel maSv = new JLabel("Mã Sinh Viên");
                maSv.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN1TK = new JTextField();
                tfTTCN1TK.setPreferredSize(new Dimension(200, 30));
                MaSV.add(maSv);
                MaSV.add(tfTTCN1TK);
                pnTTCN1.add(MaSV);

                //CENTER-TenSV
                JPanel TenSV = new JPanel(new FlowLayout());
                JLabel TenSv = new JLabel("Họ Và Tên");
                TenSv.setPreferredSize(new Dimension(100, 30));
                TenSv.setSize(100, 100);
                JTextField tfTTCN2TK = new JTextField();
                
                tfTTCN2TK.setPreferredSize(new Dimension(200, 30));
                TenSV.add(TenSv);
                TenSV.add(tfTTCN2TK);
                pnTTCN1.add(TenSV);

                //CENTER-Nam Sinh
                JPanel NamSinh = new JPanel(new FlowLayout());
                JLabel namSinh = new JLabel("Năm Sinh");
                namSinh.setPreferredSize(new Dimension(100, 30));
                namSinh.setSize(100, 100);
                JTextField tfTTCN3TK = new JTextField();
                tfTTCN3TK.setPreferredSize(new Dimension(200, 30));
                NamSinh.add(namSinh);
                NamSinh.add(tfTTCN3TK);
                pnTTCN1.add(NamSinh);

                //CENTER-Giới Tính
                 JPanel GioiTinh = new JPanel(new FlowLayout());
                JLabel gioitinh = new JLabel("Giới Tính");
                String[] luaChonTTCN = {"","Nam", "Nữ"};
                JComboBox<String> LuaChonTTCNTK = new JComboBox<>(luaChonTTCN);
                gioitinh.setPreferredSize(new Dimension(100, 30));
                LuaChonTTCNTK.setPreferredSize(new Dimension(200, 30));
                GioiTinh.add(gioitinh);
                GioiTinh.add(LuaChonTTCNTK);
                pnTTCN1.add(GioiTinh);

                //CENTER-Dân Tộc
                JPanel DanToc = new JPanel(new FlowLayout());
                JLabel dantoc = new JLabel("Dân Tộc");
                dantoc.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN5TK = new JTextField();
                tfTTCN5TK.setPreferredSize(new Dimension(200, 30));
                DanToc.add(dantoc);
                DanToc.add(tfTTCN5TK);
                pnTTCN1.add(DanToc);

                //CENTER-CCCD
                JPanel CCCD = new JPanel(new FlowLayout());
                JLabel cccd = new JLabel("CCCD/CMND");
                cccd.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN6TK = new JTextField();
                tfTTCN6TK.setPreferredSize(new Dimension(200, 30));
                CCCD.add(cccd);
                CCCD.add(tfTTCN6TK);
                pnTTCN1.add(CCCD);

                //CENTER-Sđt
                JPanel SDT = new JPanel(new FlowLayout());
                JLabel sdt = new JLabel("Số Điện Thoại");
                sdt.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN7TK = new JTextField();
                tfTTCN7TK.setPreferredSize(new Dimension(200, 30));
                SDT.add(sdt);
                SDT.add(tfTTCN7TK);
                pnTTCN1.add(SDT);

                //Mã Khoa
                JPanel KHOA = new JPanel(new FlowLayout());
                JLabel khoa = new JLabel("Tên Khoa");
                khoa.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN8TK = new JTextField();
                tfTTCN8TK.setPreferredSize(new Dimension(200, 30));
                KHOA.add(khoa);
                KHOA.add(tfTTCN8TK);
                pnTTCN1.add(KHOA);

                //Mã Lớp
                JPanel LOP = new JPanel(new FlowLayout());
                JLabel lop = new JLabel("Tên Lớp");
                lop.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN9TK = new JTextField();
                tfTTCN9TK.setPreferredSize(new Dimension(200, 30));
                LOP.add(lop);
                LOP.add(tfTTCN9TK);
                pnTTCN1.add(LOP);
                
                //Tên Cố Vấn
                JPanel COVAN = new JPanel(new FlowLayout());
                JLabel covan = new JLabel("Tên Cố Vấn");
                covan.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN10TK = new JTextField();
                tfTTCN10TK.setPreferredSize(new Dimension(200, 30));
                COVAN.add(covan);
                COVAN.add(tfTTCN10TK);
                pnTTCN1.add(COVAN);
                
                JButton btXacNhan=new JButton("Xác Nhận");
                btXacNhan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String maSinhVien = tfTTCN1TK.getText();
                    String tenSinhVien = tfTTCN2TK.getText();
                    String namSinh = tfTTCN3TK.getText();
                    String gioiTinh =(String) LuaChonTTCNTK.getSelectedItem();
                    String danToc = tfTTCN5TK.getText();
                    String CCCD = tfTTCN6TK.getText();
                    String soDienThoai = tfTTCN7TK.getText();
                    String tenKhoa = tfTTCN8TK.getText();
                    String tenLop = tfTTCN9TK.getText();
                    String tenCoVan = tfTTCN10TK.getText();
                    SinhVien sv1=new SinhVien(maSinhVien,tenSinhVien,namSinh,gioiTinh,danToc,CCCD,soDienThoai,tenCoVan,tenKhoa,tenLop);
                   
                    DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                    model.setRowCount(0);
                    ArrayList<SinhVien> svListTK = SinhVienDAO.selectBySpecialCondition( sv1);
                    for (SinhVien sv : svListTK) {
                        Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(),sv.getDanToc(),sv.getCCCD(),sv.getSoDienThoai(),sv.getTenLop(),sv.getTenKhoa(),sv.getTenCoVan()};
                        modelDSSV.addRow(duLieu);
                    }
                }
            });
                JButton thoat=new JButton("Thoát");
                thoat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btTimKiem.dispose();
                }
                });
                pnTTCN.add(pnTTCN1);
                btTimKiem.add(pnTTCN,BorderLayout.CENTER);
                JPanel pnXacNhan=new JPanel(new FlowLayout());
                pnXacNhan.add(btXacNhan);
                pnXacNhan.add(thoat);
                btTimKiem.add(pnXacNhan,BorderLayout.SOUTH);
            }
        });
        
        
        JButton xemdiem = new JButton("Xem điểm");
        JButton xemtkb = new JButton("Xem TKB");
        pn3.setLayout(new FlowLayout());
        pn3.add(them);
        pn3.add(sua);
        pn3.add(xoa);
        pn3.add(timKiem);
        pn3.add(xemdiem);
        pn3.add(xemtkb);
        pn3.setPreferredSize(new Dimension(0, 30));
        JPanel pnp = new JPanel();
        pnp.setPreferredSize(new Dimension(0, 150));
        pnp.setLayout(new BoxLayout(pnp, BoxLayout.Y_AXIS));
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
        
        //Tìm Kiếm GV        
        String[] TIMKIEMGV = {"Tất Cả","Mã Giảng Viên","Tên Giảng Viên", "Năm Sinh","Giới Tính","Trình Độ","Số Điện Thoại","Email"};
        JComboBox<String> DULIEUGV = new JComboBox<>(TIMKIEMGV);
        
        
        JTextField jcbgv = new JTextField();
        JButton btlocgv = new JButton("Lọc >>");
        locgv.add(DULIEUGV);
        locgv.add(jcbgv);
        locgv.add(btlocgv);
        JPanel chonlocgv = new JPanel(new GridLayout(1, 2));
        chonlocgv.add(locgv);
        chonlocgv.add(new JPanel());
        
        String columnDSGV[] = {"Mã GV", "Tên giảng viên","Giới Tính","Năm Sinh","Trình độ", "Số điện thoại", "Email","Mã Khoa"};
        DefaultTableModel modelDSGV = new DefaultTableModel(columnDSGV, 0);
        
        ArrayList<GiangVien> gvList=GiangVienDAO.selectAll();
        for (GiangVien gv : gvList){
            Object[] duLieu = {gv.getMaGiangVien(), gv.getTenGiangVien(),gv.getGioiTinh(), gv.getNamSinh(), gv.getTrinhDo(),gv.getSdt(),gv.getEmail(),gv.getMaKhoa()};
            modelDSGV.addRow(duLieu);
        }
        JTable bangDSGV = new JTable(modelDSGV);
        
        bangDSGV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSGV = new JScrollPane(bangDSGV);
        bangDSGV.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModelgv = bangDSGV.getColumnModel();
        
        //Kich Thước Cột
        columnModelgv.getColumn(0).setPreferredWidth(30); 
        columnModelgv.getColumn(1).setPreferredWidth(130);
        columnModelgv.getColumn(2).setPreferredWidth(30);
        columnModelgv.getColumn(6).setPreferredWidth(130);

        
        
        JPanel pn4 = new JPanel();
        
        JDatePicker namSinhGV = new JDateComponentFactory().createJDatePicker(dateModel);
        namSinhGV.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        namSinhGV.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        namSinhGV.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel sinhGV=new JPanel(new BorderLayout());
        sinhGV.add((Component) namSinhGV,BorderLayout.CENTER);
        
        JLabel lbNamSinh = new JLabel("Năm Sinh ");
         JTextField khoaGV = new JTextField();
        JLabel lbkhoa = new JLabel("Mã Khoa ");
        JTextField mGV = new JTextField();
        khoaGV.setPreferredSize(new Dimension(80, 30));
        JLabel lbmg = new JLabel("Mã Giảng Viên ");
        JTextField tenGV = new JTextField();
        JLabel lbtg = new JLabel("Tên Giảng Viên ");
        String[] gendersGV = {"Nam", "Nữ"};
        JComboBox<String> gioitinhGV = new JComboBox<>(genders);
        JLabel lbgGV = new JLabel("Giới Tính ");
        JTextField trinhdo = new JTextField();
        JLabel lbtd = new JLabel("Trình Độ ");
        JTextField sdtgv = new JTextField();
        JLabel lbsg = new JLabel("Số Điện Thoại ");
        JTextField email = new JTextField();
        JLabel lbe = new JLabel("Email ");
        pn4.setLayout(new FlowLayout());
        pn4.add(lbmg);
        pn4.add(mGV);
        mGV.setPreferredSize(new Dimension(80, 30));
        pn4.add(lbtg);
        pn4.add(tenGV);
        tenGV.setPreferredSize(new Dimension(150, 30));
        pn4.add(lbNamSinh);
        pn4.add(sinhGV);
        pn4.add(lbkhoa);
        pn4.add(khoaGV);
       
        
        JPanel pn5 = new JPanel();
        pn5.setLayout(new FlowLayout());
        pn5.add(lbgGV);
        pn5.add(gioitinhGV);
        pn5.add(lbtd);
        pn5.add(trinhdo);
        trinhdo.setPreferredSize(new Dimension(100, 30));
        pn5.add(lbsg);
        pn5.add(sdtgv);
        sdtgv.setPreferredSize(new Dimension(130, 30));
        pn5.add(lbe);
        pn5.add(email);
        email.setPreferredSize(new Dimension(180, 30));
        
        JPanel pn6 = new JPanel();
        
        //NÚT THÊM
        JButton themg = new JButton("Thêm");
        themg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MaGV = mGV.getText();
                String TenGV = tenGV.getText();
                String trinhDo = trinhdo.getText();
                String Email=email.getText();

                Date dateOfBirth = (Date) namSinhGV.getModel().getValue();
                // Định dạng lại định dạng ngày tháng năm
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(dateOfBirth);
                
                String GioiTinh = (String) gioitinhGV.getSelectedItem();
                
                String Sdt = sdtgv.getText();
                
                String maKhoa=khoaGV.getText();
                GiangVien gv=new GiangVien(MaGV,TenGV,GioiTinh,dateString,trinhDo,Sdt,Email,maKhoa);
                
                if(GiangVienDAO.insert(gv)){
                    JOptionPane.showMessageDialog(new JFrame(), "Đã Thêm Thành Công");
                    Object[] rowData = {MaGV, TenGV, GioiTinh,dateString, trinhDo, Sdt, Email,maKhoa};
                    modelDSGV.addRow(rowData);
                    bangDSGV.setModel(modelDSGV);
                    
                    mGV.setText("");
                    tenGV.setText("");
                    dantoc.setText("");
                    trinhdo.setText("");
                    gioitinh.setSelectedIndex(0);
                    sdtgv.setText("");
                    email.setText("");
                    khoaGV.setText("");
                }
                else JOptionPane.showMessageDialog(new JFrame(), "Thêm Không Thành Công");
                
            }
        });
        
        
        JButton suag = new JButton("Sửa");
        suag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bangDSGV.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một dòng để thực hiện tác vụ");
                    return;
                }
                else{
                JFrame btSua=new JFrame ("Sửa Đổi Thông Tin");
                btSua.setSize(500,500);
                btSua.setVisible(true);
                btSua.setLocationRelativeTo(null);
                btSua.setLayout(new BorderLayout());
                
                TableModel model = bangDSGV.getModel();
                int columnCount = bangDSGV.getColumnCount();
                Object value[]= new Object[columnCount];
                String GVSua[]=new String[columnCount];
                for(int i=0;i<columnCount;i++){
                    value[i] = model.getValueAt(selectedRow, i);
                    GVSua[i] = value[i].toString();
                }
                
                GiangVien gv= new GiangVien();
                gv=GiangVienDAO.selectById(GVSua[0]);
                JPanel pnTTCN= new JPanel();
                pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
                JPanel pnTTCN1 = new JPanel(new GridLayout(9, 1));

                //CENTER-MaSV
                JPanel MaGV = new JPanel(new FlowLayout());
                JLabel maGv = new JLabel("Mã Giảng Viên");
                maGv.setPreferredSize(new Dimension(100, 30));
                JLabel tfTTCN1 = new JLabel();
                tfTTCN1.setPreferredSize(new Dimension(200, 30));
                tfTTCN1.setText(GVSua[0]);
                MaGV.add(maGv);
                MaGV.add(tfTTCN1);
                pnTTCN1.add(MaGV);

                //CENTER-TenSV
                JPanel TenGV = new JPanel(new FlowLayout());
                JLabel TenGv = new JLabel("Họ Và Tên");
                TenGv.setPreferredSize(new Dimension(100, 30));
                TenGv.setSize(100, 100);
                JTextField tfTTCN2 = new JTextField();
                tfTTCN2.setText(GVSua[1]);
                tfTTCN2.setPreferredSize(new Dimension(200, 30));
                TenGV.add(TenGv);
                TenGV.add(tfTTCN2);
                pnTTCN1.add(TenGV);

                //CENTER-Nam Sinh
                JPanel NamSinh = new JPanel(new FlowLayout());
                JLabel namSinh = new JLabel("Năm Sinh");
                namSinh.setPreferredSize(new Dimension(100, 30));
                namSinh.setSize(100, 100);
                JTextField tfTTCN3 = new JTextField();
                tfTTCN3.setText(GVSua[3]);
                tfTTCN3.setPreferredSize(new Dimension(200, 30));
                NamSinh.add(namSinh);
                NamSinh.add(tfTTCN3);
                pnTTCN1.add(NamSinh);

                //CENTER-Giới Tính
                 JPanel GioiTinh = new JPanel(new FlowLayout());
                JLabel gioitinh = new JLabel("Giới Tính");
                String[] luaChonTTCN = {"Nam", "Nữ"};
                JComboBox<String> LuaChonTTCN = new JComboBox<>(luaChonTTCN);
                if(GVSua[2].equalsIgnoreCase("Nam")){
                    LuaChonTTCN.setSelectedIndex(0);
                }
                else LuaChonTTCN.setSelectedIndex(1);
                gioitinh.setPreferredSize(new Dimension(100, 30));
                LuaChonTTCN.setPreferredSize(new Dimension(200, 30));
                GioiTinh.add(gioitinh);
                GioiTinh.add(LuaChonTTCN);
                pnTTCN1.add(GioiTinh);

                //CENTER-Dân Tộc
                JPanel DanToc = new JPanel(new FlowLayout());
                JLabel dantoc = new JLabel("Trình Độ");
                dantoc.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN5 = new JTextField();
                tfTTCN5.setText(GVSua[4]);
                tfTTCN5.setPreferredSize(new Dimension(200, 30));
                DanToc.add(dantoc);
                DanToc.add(tfTTCN5);
                pnTTCN1.add(DanToc);

                //CENTER-CCCD
                JPanel CCCD = new JPanel(new FlowLayout());
                JLabel cccd = new JLabel("Email");
                cccd.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN6 = new JTextField();
                tfTTCN6.setText(GVSua[6]);
                tfTTCN6.setPreferredSize(new Dimension(200, 30));
                CCCD.add(cccd);
                CCCD.add(tfTTCN6);
                pnTTCN1.add(CCCD);

                //CENTER-Sđt
                JPanel SDT = new JPanel(new FlowLayout());
                JLabel sdt = new JLabel("Số Điện Thoại");
                sdt.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN7 = new JTextField();
                tfTTCN7.setText(GVSua[5]);
                tfTTCN7.setPreferredSize(new Dimension(200, 30));
                SDT.add(sdt);
                SDT.add(tfTTCN7);
                pnTTCN1.add(SDT);

                //Mã Khoa
                JPanel KHOA = new JPanel(new FlowLayout());
                JLabel khoa = new JLabel("Mã Khoa");
                khoa.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN8 = new JTextField();
                tfTTCN8.setText(GVSua[7]);
                tfTTCN8.setPreferredSize(new Dimension(200, 30));
                KHOA.add(khoa);
                KHOA.add(tfTTCN8);
                pnTTCN1.add(KHOA);

                

                pnTTCN.add(pnTTCN1);
                btSua.add(pnTTCN,BorderLayout.CENTER);
                
                JPanel btXacNhan=new JPanel(new FlowLayout());
                JButton XacNhan=new JButton("Xác Nhận");
                XacNhan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String gioiTinh = (String) LuaChonTTCN.getSelectedItem();
                        String TenGV=tfTTCN2.getText();
                        String namSinh=tfTTCN3.getText();             
                        String trinhDo=tfTTCN5.getText();
                        String email=tfTTCN6.getText();
                        String sdt=tfTTCN7.getText();
                        String maKhoa=tfTTCN8.getText();

                        GiangVien gv=new GiangVien(GVSua[0],TenGV,gioiTinh,namSinh,trinhDo,sdt,email,maKhoa);
                        if(GiangVienDAO.update(gv)&&TenGV!=null&&namSinh!=null&&trinhDo!=null&&email!=null&&sdt!=null&&maKhoa!=null){
                            JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Thành Công");
                            bangDSGV.setValueAt(TenGV,selectedRow, 1);
                            bangDSGV.setValueAt(namSinh,selectedRow, 3);
                            bangDSGV.setValueAt(gioiTinh,selectedRow, 2);
                            bangDSGV.setValueAt(trinhDo,selectedRow, 4);
                            bangDSGV.setValueAt(email,selectedRow, 6);
                            bangDSGV.setValueAt(sdt,selectedRow, 5);
                            bangDSGV.setValueAt(maKhoa,selectedRow, 7);
                            btSua.dispose();
                        }
                        else JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Không Thành Công");
                    } 
            });
                JButton Huy=new JButton("Hủy");
                Huy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            btSua.dispose();
                        } 
            });
                btXacNhan.add(XacNhan);
                btXacNhan.add(Huy);
                btSua.add(btXacNhan,BorderLayout.SOUTH);
            
            }
            }
        });
            
        //NÚT XÓA
        JButton xoag = new JButton("Xóa");
        xoag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel model = bangDSGV.getModel();
                int selectedRow = bangDSGV.getSelectedRow();
                Object value = model.getValueAt(selectedRow, 0); // Lấy giá trị của ô đầu tiên trong dòng đã chọn
                // Lưu giá trị vào biến
                String MaGVXoa = value.toString();
                
                if (GiangVienDAO.delete(MaGVXoa)){
                    JOptionPane.showMessageDialog(new JFrame(), "Đã Xóa Thành Công");
                    if (selectedRow != -1) {
                    modelDSGV.removeRow(selectedRow);
                }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "Không Tìm Thấy Sinh Vien Cần Xóa");
            }
        });
        JButton xemlichgiang = new JButton("Xem lịch giảng");
        pn6.setLayout(new FlowLayout());
        pn6.add(themg);
        pn6.add(suag);
        pn6.add(xoag);
        pn6.add(xemlichgiang);
        pn6.setPreferredSize(new Dimension(0, 55));
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(0, 150));
        pn.setLayout(new BoxLayout(pn,BoxLayout.Y_AXIS));
        pn.add(pn4);
        pn.add(pn5);
        pn.add(pn6);
        pn.add(chonlocgv);
        pn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSGV.add(scrollPaneDSGV, BorderLayout.CENTER);
        DSGV.add(pn, BorderLayout.NORTH);

        //DSMH
        JPanel locmh = new JPanel();
        locmh.setLayout(new BoxLayout(locmh, BoxLayout.X_AXIS));
        String[] TIMKIEMMH = {"Tất Cả","Mã Môn Học","Mã Giảng Viên", "Tiết Bắt Đầu","Ngày Bắt Đầu","Ngày Kết Thúc"};
        JComboBox<String> DULIEUMH = new JComboBox<>(TIMKIEMMH);
        
        JTextField jcbmh = new JTextField();
        JButton btlocmh = new JButton("Lọc >>");
        locmh.add(DULIEUMH);
        locmh.add(jcbmh);
        locmh.add(btlocmh);
        JPanel chonlocmh = new JPanel(new GridLayout(1, 2));
        chonlocmh.add(locmh);
        chonlocmh.add(new JPanel());
        
        JPanel DSMH = new JPanel(new BorderLayout());
        String columnDSMH[] = {"Mã MH", "Tên môn học", "Tiết bắt đầu", "Mã Giảng Viên", "Ngày bắt đầu","Ngày Kết Thúc"};
        DefaultTableModel modelDSMH = new DefaultTableModel(columnDSMH, 0);
        JTable bangDSMH = new JTable(modelDSMH);
        bangDSMH.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSMH = new JScrollPane(bangDSMH);
        JPanel pn7 = new JPanel();
        JTextField mMH = new JTextField();
        mMH.setPreferredSize(new Dimension(120, 30));
        JLabel lbmmh = new JLabel("Mã MH ");
        lbmmh.setPreferredSize(new Dimension(60, 30));
        JTextField tenMH = new JTextField();
        tenMH.setPreferredSize(new Dimension(170, 30));
        JLabel lbtm = new JLabel("Tên môn học ");
        String[] tbd = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<String> tietbatdau = new JComboBox<>(tbd);
        JLabel lbtbd = new JLabel("Tiết bắt đầu ");
        String[] soTiet = {"1", "2", "3", "4"};
        JComboBox<String> sotiet = new JComboBox<>(soTiet);
        JLabel lbst = new JLabel("Số tiết ");
        JTextField giangvien  = new JTextField();
        giangvien.setPreferredSize(new Dimension(100, 30));
        JLabel lbtgv = new JLabel("Mã Giảng viên ");
        
        JDatePicker ngayBatDau = new JDateComponentFactory().createJDatePicker(dateModel);
        ngayBatDau.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        ngayBatDau.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        ngayBatDau.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel ngayBD=new JPanel(new BorderLayout());
        ngayBD.add((Component) ngayBatDau,BorderLayout.CENTER);
        
        JDatePicker ngayKetThuc = new JDateComponentFactory().createJDatePicker(dateModel);
        ngayKetThuc.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        ngayKetThuc.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        ngayKetThuc.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel ngayKT=new JPanel(new BorderLayout());
        ngayKT.add((Component) ngayKetThuc,BorderLayout.CENTER);
        
        JLabel lbnbd = new JLabel("Ngày bắt đầu ");
        JLabel lbnkt = new JLabel("Ngày kết thúc ");
        pn7.setLayout(new FlowLayout());
        pn7.add(lbmmh);
        pn7.add(mMH);
        pn7.add(lbtm);
        pn7.add(tenMH);
        pn7.add(lbtbd);
        pn7.add(tietbatdau);
        pn7.add(lbst);
        pn7.add(sotiet);
        
        pn7.setPreferredSize(new Dimension(0, 50));
        JPanel pn8 = new JPanel();
        pn8.setLayout(new FlowLayout());
        pn8.add(lbtgv);
        pn8.add(giangvien);
        pn8.add(lbnbd);
        pn8.add(ngayBD);
        pn8.add(lbnkt);
        pn8.add(ngayKT);
        pn8.setPreferredSize(new Dimension(0, 50));
        JPanel pn9 = new JPanel();
        JButton t = new JButton("Thêm");
        JButton s = new JButton("Sửa");
        JButton x = new JButton("Xóa");
        pn9.setLayout(new FlowLayout());
        pn9.add(t);
        pn9.add(s);
        pn9.add(x);
        pn9.setPreferredSize(new Dimension(0, 55));
        JPanel pnmh = new JPanel();
        pnmh.setPreferredSize(new Dimension(0, 150));
        pnmh.setLayout(new BoxLayout(pnmh,BoxLayout.Y_AXIS));
        pnmh.add(pn7);
        pnmh.add(pn8);
        pnmh.add(pn9);
        pnmh.add(chonlocmh);
        pnmh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSMH.add(scrollPaneDSMH, BorderLayout.CENTER);
        DSMH.add(pnmh, BorderLayout.NORTH);
        
        
        // Phòng học
        
        
        
        JPanel DSPH = new JPanel(new BorderLayout());
        JPanel lochp = new JPanel();
        lochp.setLayout(new BoxLayout(lochp, BoxLayout.X_AXIS));
        JTextField jcbhp = new JTextField();
        JButton btlochp = new JButton("Lọc >>");
        lochp.add(DULIEUGV);
        lochp.add(jcbhp);
        lochp.add(btlochp);
        JPanel chonlochp = new JPanel(new GridLayout(1, 2));
        chonlochp.add(lochp);
        chonlochp.add(new JPanel());
        
        String columnDSPH[] = {"Mã Phòng học", "Tên Phòng học", "Chức Năng","Mã Khoa Quản Lý"};
        DefaultTableModel modelDSPH = new DefaultTableModel(columnDSPH, 0);
        
        ArrayList<PhongHoc> phList = PhongHocDAO.selectAll();
        for (PhongHoc ph : phList) {
            Object[] duLieu = {ph.getMaPhong(), ph.getTenPhong(), ph.getChucNang(), ph.getMaKhoaQuanLy()};
            modelDSPH.addRow(duLieu);
        }
        JTable bangDSPH = new JTable(modelDSPH);
        bangDSPH.getTableHeader().setResizingAllowed(false);
        
        
        //Kich Thước Cột
        
        
        bangDSPH.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSPH = new JScrollPane(bangDSPH);
        
        
        JTextField jtfloc = new JTextField();
        jtfloc.setEnabled(false);
        String[] TIMKIEMPH = {"Tất Cả","Mã Phòng","Tên Phòng", "Chức Năng","Khoa quản lý"};
        JComboBox<String> DULIEUP = new JComboBox<>(TIMKIEMPH);
        
        
        DULIEUP.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DULIEUP.getSelectedIndex() == 0) {
                // Nếu chọn phần tử đầu tiên thì không cho phép chỉnh sửa trường văn bản
                jtfloc.setEnabled(false);
            } else {
                // Nếu chọn bất kỳ phần tử nào khác thì cho phép chỉnh sửa trường văn bản
                jtfloc.setEnabled(true);
            }
        }
    });

        
     
        JButton btLoc = new JButton("Lọc >>");
        btLoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag=false;
                String luachonTKPH = (String) DULIEU.getSelectedItem();                     
                String NDTKPH=new String();
                if(luachonTKPH.equalsIgnoreCase("Tất Cả")){
                    DefaultTableModel model = (DefaultTableModel) bangDSPH.getModel();
                    model.setRowCount(0);
                    ArrayList<PhongHoc> phList = PhongHocDAO.selectAll();
                    for (PhongHoc ph : phList) {
                        Object[] duLieuP = {ph.getMaPhong(), ph.getTenPhong(), ph.getChucNang(), ph.getMaKhoaQuanLy()};
                        modelDSPH.addRow(duLieuP);
                        flag=true;
                    }
                }
                else if(luachonTKPH.equalsIgnoreCase("Mã Phòng: "))    NDTKPH="maPhong";
                else if(luachonTKPH.equalsIgnoreCase("Tên Phòng: ")) NDTKPH="tnPhong";
                else if(luachonTKPH.equalsIgnoreCase("Chức năng: ")) NDTKPH="chucNang";
                else if(luachonTKPH.equalsIgnoreCase("Mã Khoa quản lý")) NDTKPH="maKhoaQuanLy";
                if(!flag){
                ArrayList<PhongHoc> list=PhongHocDAO.selectByCondition(NDTKPH, jtfloc.getText());
                DefaultTableModel model = (DefaultTableModel) bangDSPH.getModel();
                model.setRowCount(0);
                for (PhongHoc ph : list) {
                    Object[] duLieuTimKiem = {ph.getMaPhong(), ph.getTenPhong(), ph.getChucNang(), ph.getMaKhoaQuanLy()};
                    modelDSPH.addRow(duLieuTimKiem);
                    }
                }
            }
        });
        
        
        JPanel pn10 = new JPanel();
        
        

        JTextField mph = new JTextField();
        mph.setPreferredSize(new Dimension(130, 30));
        JLabel lbmp = new JLabel("Mã Phòng ");
        
        JTextField tenph = new JTextField();
        tenph.setPreferredSize(new Dimension(200, 30));
        JLabel lbtp = new JLabel("Tên Phòng ");
        
        JTextField chucnang = new JTextField();
        chucnang.setPreferredSize(new Dimension(200, 30));
        JLabel lbcn = new JLabel("Chức Năng ");
        
        JLabel lbkql = new JLabel("Mã Khoa Quản Lý ");
        JTextField khoaql = new JTextField();
        khoaql.setPreferredSize(new Dimension(70, 30));
        
        pn10.setLayout(new FlowLayout());
        pn10.add(lbmp);
        pn10.add(mph);
        pn10.add(lbtp);
        pn10.add(tenph);
        
        pn10.setPreferredSize(new Dimension(0, 50)); 
        
        JPanel pn11=new JPanel(new FlowLayout());
        pn11.add(lbcn);
        pn11.add(chucnang);
        pn11.add(lbkql);
        pn11.add(khoaql);
    
       
        JPanel pn12 = new JPanel();
        
        
        //Button thêm
        JButton themph = new JButton("Thêm");
        themph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
            }
        });
        
        
        //Button sửa
        JButton suaph = new JButton("Sửa");
        
        //Button xóa
        JButton xoaph = new JButton("Xóa");
        xoaph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        
        
        //Button tìm kiếm
        JButton timKiemph = new JButton("Tìm Kiếm");
        
        
        pn12.setLayout(new FlowLayout());
        pn12.add(themph);
        pn12.add(suaph);
        pn12.add(xoaph);
        pn12.add(timKiemph);
        pn12.setPreferredSize(new Dimension(0, 30));
        JPanel panelphu = new JPanel();
        panelphu.setPreferredSize(new Dimension(0, 150));
        panelphu.setLayout(new BoxLayout(panelphu, BoxLayout.Y_AXIS));
        panelphu.add(pn10);
        panelphu.add(pn11);
        panelphu.add(pn12);
        panelphu.add(chonlochp);

        panelphu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSPH.add(scrollPaneDSPH, BorderLayout.CENTER);
        DSPH.add(panelphu, BorderLayout.NORTH);
        

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
        thanhChon.addTab("Danh Sách Sinh Viên", DSSV);
        thanhChon.addTab("Danh Sách Giảng Viên", DSGV);
        thanhChon.addTab("Danh Sách Môn Học", DSMH);
        thanhChon.addTab("Danh Sách Phòng Học", DSPH);
        thanhChon.addTab("Học phí", TTHP);

        //Thêm Vào Jframe
        add(titlePn, BorderLayout.NORTH);
        add(thanhChon, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.SOUTH);

    }
    public static void main(String[] args){
        new GiaoDienQuanLy();
    }
}