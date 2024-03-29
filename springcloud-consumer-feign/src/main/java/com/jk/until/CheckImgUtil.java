package com.jk.until;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckImgUtil {
    public static void buildCheckImg(HttpServletRequest request, HttpServletResponse response) {
        try {
            checkImg(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取验证码图片并生成到前台
    public static String checkImg(HttpServletRequest request,HttpServletResponse response) throws Exception{
        int width = 120;
        int height = 30;

        // 步骤一 绘制一张内存中图片
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 步骤二 图片绘制背景颜色 ---通过绘图对象
        Graphics graphics = bufferedImage.getGraphics();// 得到画图对象 --- 画笔
        // 绘制任何图形之前 都必须指定一个颜色
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);

        // 步骤三 绘制边框
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, width - 1, height - 1);

        // 步骤四 四个随机数字
        Graphics2D graphics2d = (Graphics2D) graphics;
        // 设置输出字体
        graphics2d.setFont(new Font("宋体", Font.BOLD, 18));





        //定义一个包含大小写字母和数组的字符串
        String words ="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //生成一个随机数
        Random random = new Random();// 生成随机数
        // 定义StringBuffer  用来拼接生成的四位验证码
        StringBuffer sb = new StringBuffer();
        // 定义x坐标
        int x = 10;
        for (int i = 0; i < 4; i++) {
            // 随机颜色
            graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 旋转 -30 --- 30度
            int jiaodu = random.nextInt(60) - 30;
            // 换算弧度
            double theta = jiaodu * Math.PI / 180;

            // 生成一个随机数字  用来作为字符串的下标
            int index = random.nextInt(words.length()); // 生成随机数 0 到 length - 1
            // 获得字母数字   charAt() 通过下标取字符串中对应的字符
            char c = words.charAt(index);
            //将每次循环得到的一个随机字符拼接到 sb 变量中
            sb.append(c);
            // 将c 输出到图片
            graphics2d.rotate(theta, x, 20);
            graphics2d.drawString(String.valueOf(c), x, 20);
            graphics2d.rotate(-theta, x, 20);
            x += 30;
        }
        //******将生成的四位验证码存到session中
        request.getSession().setAttribute("checkcode", sb.toString());

        //对验证码图片样式的设置
        graphics.setColor(getRandColor(160, 200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
        }

        // 将上面图片输出到浏览器 ImageIO
        graphics.dispose();// 释放资源
        ImageIO.write(bufferedImage, "jpg",response.getOutputStream());
        return null;
    }

    /**
     * 取其某一范围的color 给验证码图片进行设置
     * @param fc
     *            int 范围参数1
     * @param bc
     *            int 范围参数2
     * @return Color
     */
    private static Color getRandColor(int fc, int bc) {
        // 取其随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
