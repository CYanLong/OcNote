package com.ocnote.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

	
	
	@RequestMapping(value="/getVerifyCodeImage", method=RequestMethod.GET)
	public void getValidIamge(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		
		response.setContentType("image/jpeg");
		
		int width = 120;
		int height = 25;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB); // ����BufferedImage��Ķ���
		
		Graphics g = image.getGraphics(); // ����Graphics��Ķ���
		Random random = new Random(); // ʵ����һ��Random����
		
		Font mFont = new Font("��������", Font.BOLD, 22); //Font.BOLD:��ʶ������ʽ����.��Ӧ�� Font.ITALIC(б��)  Font.PLAIN(��ͨ��ʽ����.)
		
		g.setColor(getRandColor(200, 250)); // �ı�ͼ�εĵ�ǰ��ɫΪ������ɵ���ɫ
		g.fillRect(0, 0, width, height); //The rectangle is filled using the graphics context's current color.

		// ���ɲ�����������֤����
		g.setFont(mFont);
		String sRand = "";
		int itmp = 0;
		for (int i = 0; i < 4; i++) {
			if (random.nextInt(2) == 1) { //zero (inclusive) and bound (exclusive)
				itmp = random.nextInt(26) + 65; // ����A~Z����ĸ
			} else {
				itmp = random.nextInt(10) + 48; // ����0~9������
			}
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			Color color = new Color(20 + random.nextInt(110),
					20 + random.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);
			/**** ����������ֲ���������תָ���Ƕ� **/
			// ��������תָ���Ƕ�
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((random.nextInt()%30) * Math.PI / 180, 27 * i + 10, 7); //45����. 
			// ��������
			float scaleSize = random.nextFloat() + 0.8f;//from the range 0.0f (inclusive) to 1.0f (exclusive), 
			if (scaleSize > 1.1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			/************************/
			g.drawString(String.valueOf(ctmp), 27 * i + 7, 18);//i=0 to 3
		}
		// �����ɵ���֤�뱣�浽Session��
		HttpSession session = request.getSession(true);
		session.setAttribute("code", sRand);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r = s + random.nextInt(e - s);
		int g = s + random.nextInt(e - s);
		int b = s + random.nextInt(e - s);
		return new Color(r, g, b);
	}
	
	@ResponseBody
	@RequestMapping(value="/verifyCode", method=RequestMethod.GET)
	public boolean validImage(HttpServletRequest request, String vCode){
		String code = (String)request.getSession().getAttribute("code");
		if(code != null && code.equals(vCode)){
			return true;
		}
		return false;
	}
}
