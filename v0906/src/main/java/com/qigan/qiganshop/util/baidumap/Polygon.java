package com.qigan.qiganshop.util.baidumap;
/**
 * 检测一个点是否在多边形中
 *
 * @author wanghao
 * @time 2019-04-17 18:38
 */

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Polygon {


    public static boolean checkIn(String testPoint, String apexPoints) {
        String[] split = testPoint.split(",");
        if (split.length != 2) {
            return false;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("X", split[0]);
        hashMap.put("Y", split[1]);

        return isInPolygon(hashMap, apexPoints);
    }

    /**
     * 判断当前位置是否在多边形区域内
     *
     * @param orderLocation     当前点
     * @param partitionLocation 区域顶点
     * @return
     */
    public static boolean isInPolygon(HashMap<String, String> orderLocation, String partitionLocation) {

        double p_x = Double.parseDouble(orderLocation.get("X"));
        double p_y = Double.parseDouble(orderLocation.get("Y"));
        Point2D.Double point = new Point2D.Double(p_x, p_y);

        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        String[] strList = partitionLocation.split(",");

        for (String str : strList) {
            String[] points = str.split("_");
            double polygonPoint_x = Double.parseDouble(points[0]);
            double polygonPoint_y = Double.parseDouble(points[1]);
            Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
            pointList.add(polygonPoint);
        }
        return IsPtInPoly(point, pointList);
    }

    /**
     * 返回一个点是否在一个多边形区域内， 如果点位于多边形的顶点或边上，不算做点在多边形内，返回false
     *
     * @param point
     * @param polygon
     * @return
     */
    public static boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            p.lineTo(d.x, d.y);
        }
        p.lineTo(first.x, first.y);
        p.closePath();
        return p.contains(point);
    }

    /**
     * 判断点是否在多边形内，如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
     *
     * @param point 检测点
     * @param pts   多边形的顶点
     * @return 点在多边形内返回true, 否则返回false
     */
    public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts) {

        int N = pts.size();
        //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        boolean boundOrVertex = true;
        //cross points count of x
        int intersectCount = 0;
        //浮点类型计算时候与0比较时候的容差
        double precision = 2e-10;
        //neighbour bound vertices
        Point2D.Double p1, p2;
        //当前点
        Point2D.Double p = point;

        // 左边顶点
        p1 = pts.get(0);
        for (int i = 1; i <= N; ++i) {
            // 判断是否恰好在顶点上
            if (p.equals(p1)) {
                // 如果在,直接返回 true
                return true;
            }
            // 右边顶点
            p2 = pts.get(i % N);
            /**
             * 如果测试点比左顶点小或者比右顶点大,说明不在这两个点之间
             * 然后左顶点的值取右顶点
             */
            //ray is outside of our interests
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {
                p1 = p2;
                /**
                 * 不在这两个点之间,然后就比较p2 和下一个点
                 */
                continue;
            }
            //ray is crossing over by the algorithm (common part of)
            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {
                //x is before of ray
                if (p.y <= Math.max(p1.y, p2.y)) {
                    //overlies on a horizontal ray
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {
                        return true;
                    }
                    //ray is vertical
                    if (p1.y == p2.y) {
                        //overlies on a vertical ray
                        if (p1.y == p.y) {
                            return true;
                            //before ray
                        } else {
                            ++intersectCount;
                        }
                    } else {//cross point on the left side
                        //cross point of y
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;
                        //overlies on a ray
                        if (Math.abs(p.y - xinters) < precision) {
                            return true;
                        }
                        //before ray
                        if (p.y < xinters) {
                            ++intersectCount;
                        }
                    }
                }
            } else {//special case when ray is crossing through the vertex
                //p crossing over p2
                if (p.x == p2.x && p.y <= p2.y) {
                    //next vertex
                    Point2D.Double p3 = pts.get((i + 1) % N);
                    //p.x lies between p1.x & p3.x
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            //next ray left point
            p1 = p2;
        }

        if (intersectCount % 2 == 0) {
            //偶数在多边形外
            return false;
        } else {
            //奇数在多边形内
            return true;
        }
    }
}
