import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Mar10Event extends JFrame implements KeyListener{
	// image object
	Image oImage;
	int x, y, sel = 1;
	int iMoveX = 10;	// イメージ移動単位
	int iMovePx = 0;	// 右移動測定変数
	int iMoveMx = 0;	// 左移動測定変数
	int iDrawPx = 20;	// イメージ変更単位
	
	public Mar10Event() {
		super("310(Mar10)");
		// フレーム設定
		setLayout(null);
		setResizable(false);
		setBounds(100, 100, 600, 400);
		setBackground(Color.GREEN);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		
		x = (int)getWidth() / 2;
		y = (int)getHeight() / 2;
	}
	
	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		//キー入力処理
		if (key == e.VK_RIGHT) {
			// 右カーソルボタン処理
			if (iMovePx == 0) {
				switch(sel) {
				case 1 : sel = 2;
				break;
				case 2 : sel = 3;
				break;
				case 3 : sel = 1;
				break;
				default : sel = 1;				
				}
				iMovePx = iDrawPx;
			}
		if (x < getWidth()-45) {
			x+= iMoveX;  
			}
			iMovePx -= iMoveX;
			repaint(); 
		}
		
		if (key == e.VK_LEFT) {
			// 左カーソルボタン処理
			if (iMoveMx == 0) {
				switch(sel) {
				case 4 : sel = 5;
				break;
				case 5 : sel = 6;
				break;
				case 6 : sel = 4;
				break;
				default : sel = 4;
				}
				iMoveMx = iDrawPx;
			}
			if (x > 45) {
				x-=iMoveX;
			}
			iMoveMx -= iMoveX;
			repaint(); 
		}
	}
	
	@Override
	public void update(Graphics g) {
		// ダブルバッファリング
		paint(g);
	}
	@Override
	public void paint(Graphics g) {
		String sImagePath = System.getProperty("user.dir") + "\\image\\";
		switch(sel) {
		case 1:
			oImage = Toolkit.getDefaultToolkit().getImage(sImagePath + "mario_1.png");
			break;
		case 2:
			oImage = Toolkit.getDefaultToolkit().getImage(sImagePath + "mario_2.png");
			break;
		case 3:
			oImage = Toolkit.getDefaultToolkit().getImage(sImagePath + "mario_3.png");
			break;
		case 4:
			oImage = Toolkit.getDefaultToolkit().getImage(sImagePath + "mario_4.png");
			break;
		case 5:
			oImage = Toolkit.getDefaultToolkit().getImage(sImagePath + "mario_5.png");
			break;
		case 6:
			oImage = Toolkit.getDefaultToolkit().getImage(sImagePath + "mario_6.png");
			break;
		}
		g.clearRect(0, 0, getWidth(), getHeight());
		// イメージ設定
		g.drawImage(oImage,
				x - oImage.getWidth(this) / 2,
				//y - oImage.getHeight(this) / 2,
				250,
				this);

	}
	@Override
	public void keyReleased(java.awt.event.KeyEvent arg0) {
		// キーのリリーズ時の設定
		int key = arg0.getKeyCode();
		if (key == arg0.VK_RIGHT) {
			sel = 1;
			if (x < getWidth()-45) {
			  // スライド
			  x+= 10;
			}
			iMovePx = 0;
			iMoveMx = 0;
			repaint(); 
		}
		if (key == arg0.VK_LEFT) {
			sel = 4;
			if (x > 45) {
				// スライド
				x-= 10;
			}
			iMovePx = 0;
			iMoveMx = 0;
			repaint(); 
		}
	}
	@Override
	public void keyTyped(java.awt.event.KeyEvent arg0) {}
	
	public static void main(String[] args) {
		new Mar10Event();
	}
}
