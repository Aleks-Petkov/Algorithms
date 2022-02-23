package com.williamfiset.algorithms.geometry;

import static com.google.common.truth.Truth.assertThat;
import static com.williamfiset.algorithms.geometry.LineSegmentLineSegmentIntersection.lineSegmentLineSegmentIntersection;

import org.junit.*;

public class LineSegmentLineSegmentIntersectionTest {

    @Test
    public void testFromMain() {
        // ¨test cases¨ from main
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        p1 = new LineSegmentLineSegmentIntersection.Pt(-2, 4);
        p2 = new LineSegmentLineSegmentIntersection.Pt(3, 3);
        p3 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        p4 = new LineSegmentLineSegmentIntersection.Pt(2, 4);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        LineSegmentLineSegmentIntersection.Pt point = points[0];

        // point: (1.636, 3.273)
        assertThat(point.x ).isEqualTo(1.6363636363636362);
        assertThat(point.y).isEqualTo(3.2727272727272725);

        p1 = new LineSegmentLineSegmentIntersection.Pt(-10, 0);
        p2 = new LineSegmentLineSegmentIntersection.Pt(+10, 0);
        p3 = new LineSegmentLineSegmentIntersection.Pt(-5, 0);
        p4 = new LineSegmentLineSegmentIntersection.Pt(+5, 0);
        points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        LineSegmentLineSegmentIntersection.Pt point1 = points[0], point2 = points[1];

        // point1: (-5.000, 0.000), point2: (5.000, 0.000)
        assertThat(point1.x).isEqualTo(-5.0);
        assertThat(point1.y).isEqualTo(0.0);
        assertThat(point2.x).isEqualTo(5.0);
        assertThat(point2.y).isEqualTo(0.0);
    }

    @Test
    public void testNoSegmentsIntersect() {
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        // make the segments parallel -> no intersection
        p1 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        p2 = new LineSegmentLineSegmentIntersection.Pt(1, 0);
        p3 = new LineSegmentLineSegmentIntersection.Pt(0, 1);
        p4 = new LineSegmentLineSegmentIntersection.Pt(1, 1);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);

        // no intersection exists
        assertThat(points.length).isEqualTo(0);
    }

    @Test
    public void testSegmentsEqualSamePoint() {
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        // make the segments the same point
        p1 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        p2 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        p3 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        p4 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // only one solution; (0.0)
        assertThat(points.length).isEqualTo(1);

        LineSegmentLineSegmentIntersection.Pt point = points[0];

        // point: (0, 0)
        assertThat(point.x).isEqualTo(0.0);
        assertThat(point.y).isEqualTo(0.0);
    }

    @Test
    public void testOneSegmentIsIntersectingPoint() {
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        // make segment #2 as a point and place at the endpoint of #1
        p1 = new LineSegmentLineSegmentIntersection.Pt(1, 0);
        p2 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        p3 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        p4 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // one solution; (1,4)
        assertThat(points.length).isEqualTo(1);

        LineSegmentLineSegmentIntersection.Pt point = points[0];

        assertThat(point.x).isEqualTo(1.0);
        assertThat(point.y).isEqualTo(4.0);

        // make segment #1 as a point and place at the endpoint of #2
        p1 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        p2 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        p3 = new LineSegmentLineSegmentIntersection.Pt(1, 0);
        p4 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // one solution; (1,4)
        assertThat(points.length).isEqualTo(1);

        point = points[0];

        assertThat(point.x).isEqualTo(1.0);
        assertThat(point.y).isEqualTo(4.0);
    }

    @Test
    public void testSegmentsAreEqual() {
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        // make the segments equal
        p1 = new LineSegmentLineSegmentIntersection.Pt(1, 1);
        p2 = new LineSegmentLineSegmentIntersection.Pt(2, 2);
        p3 = new LineSegmentLineSegmentIntersection.Pt(1, 1);
        p4 = new LineSegmentLineSegmentIntersection.Pt(2, 2);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // two solutions; (1,1) and (2.2)
        assertThat(points.length).isEqualTo(2);

        LineSegmentLineSegmentIntersection.Pt point1 = points[0];
        LineSegmentLineSegmentIntersection.Pt point2 = points[1];

        assertThat(point1.x).isEqualTo(1.0);
        assertThat(point1.y).isEqualTo(1.0);
        assertThat(point2.x).isEqualTo(2.0);
        assertThat(point2.y).isEqualTo(2.0);
    }

    @Test
    public void testOneSegmentEnclosedInTheOther() {
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        // put segment #2 in segment #1
        p1 = new LineSegmentLineSegmentIntersection.Pt(1, 0);
        p2 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        p3 = new LineSegmentLineSegmentIntersection.Pt(1, 2);
        p4 = new LineSegmentLineSegmentIntersection.Pt(1, 3);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // two solutions; p3 and p4
        assertThat(points.length).isEqualTo(2);

        LineSegmentLineSegmentIntersection.Pt point1 = points[0];
        LineSegmentLineSegmentIntersection.Pt point2 = points[1];

        assertThat(point1.x).isEqualTo(p3.x);
        assertThat(point1.y).isEqualTo(p3.y);
        assertThat(point2.x).isEqualTo(p4.x);
        assertThat(point2.y).isEqualTo(p4.y);

        // put segment #1 in segment #2
        p1 = new LineSegmentLineSegmentIntersection.Pt(1, 2);
        p2 = new LineSegmentLineSegmentIntersection.Pt(1, 3);
        p3 = new LineSegmentLineSegmentIntersection.Pt(1, 0);
        p4 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // two solutions; p1 and p2
        assertThat(points.length).isEqualTo(2);

        point1 = points[0];
        point2 = points[1];

        assertThat(point1.x).isEqualTo(p1.x);
        assertThat(point1.y).isEqualTo(p1.y);
        assertThat(point2.x).isEqualTo(p2.x);
        assertThat(point2.y).isEqualTo(p2.y);
    }

    @Test
    public void testCollinearSegments() {
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        // make the segments collinear but one doesn't enclose the other
        p1 = new LineSegmentLineSegmentIntersection.Pt(0, 1);
        p2 = new LineSegmentLineSegmentIntersection.Pt(4, 1);
        p3 = new LineSegmentLineSegmentIntersection.Pt(3, 1);
        p4 = new LineSegmentLineSegmentIntersection.Pt(7, 1);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // two solutions; p3 and p2
        assertThat(points.length).isEqualTo(2);

        LineSegmentLineSegmentIntersection.Pt point1 = points[0];
        LineSegmentLineSegmentIntersection.Pt point2 = points[1];

        assertThat(point1.x).isEqualTo(p3.x);
        assertThat(point1.y).isEqualTo(p3.y);
        assertThat(point2.x).isEqualTo(p2.x);
        assertThat(point2.y).isEqualTo(p2.y);

        // make the segments collinear but start #2 where #1 ends
        p1 = new LineSegmentLineSegmentIntersection.Pt(0, 1);
        p2 = new LineSegmentLineSegmentIntersection.Pt(4, 1);
        p3 = new LineSegmentLineSegmentIntersection.Pt(4, 1);
        p4 = new LineSegmentLineSegmentIntersection.Pt(7, 1);
        points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // one solution; p2 = p3
        assertThat(points.length).isEqualTo(1);

        point1 = points[0];

        assertThat(point1.x).isEqualTo(p2.x);
        assertThat(point1.y).isEqualTo(p2.y);
    }

    @Test
    public void testOneSegmentIsVertical() {
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        // make segment #1 vertical and #2 horizontal
        p1 = new LineSegmentLineSegmentIntersection.Pt(1, 0);
        p2 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        p3 = new LineSegmentLineSegmentIntersection.Pt(0, 2);
        p4 = new LineSegmentLineSegmentIntersection.Pt(4, 2);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // one solution; (1,2)
        assertThat(points.length).isEqualTo(1);

        LineSegmentLineSegmentIntersection.Pt point = points[0];

        assertThat(point.x).isEqualTo(1.0);
        assertThat(point.y).isEqualTo(2.0);


        // make segment #2 vertical and #2 horizontal
        p1 = new LineSegmentLineSegmentIntersection.Pt(0, 2);
        p2 = new LineSegmentLineSegmentIntersection.Pt(4, 2);
        p3 = new LineSegmentLineSegmentIntersection.Pt(1, 0);
        p4 = new LineSegmentLineSegmentIntersection.Pt(1, 4);
        points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // one solution; (1,2)
        assertThat(points.length).isEqualTo(1);

        point = points[0];

        assertThat(point.x).isEqualTo(1.0);
        assertThat(point.y).isEqualTo(2.0);
    }

    @Test
    public void testUniqueIntersectionPoint() {
        // Segment #1 is (p1, p2), segment #2 is (p3, p4)
        LineSegmentLineSegmentIntersection.Pt p1, p2, p3, p4;

        // make segment #1 and #2 cross like an X
        p1 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        p2 = new LineSegmentLineSegmentIntersection.Pt(4, 4);
        p3 = new LineSegmentLineSegmentIntersection.Pt(0, 4);
        p4 = new LineSegmentLineSegmentIntersection.Pt(4, 0);
        LineSegmentLineSegmentIntersection.Pt[] points = lineSegmentLineSegmentIntersection(p1, p2, p3, p4);
        // one solution; (2,2)
        assertThat(points.length).isEqualTo(1);

        LineSegmentLineSegmentIntersection.Pt point = points[0];

        assertThat(point.x).isEqualTo(2.0);
        assertThat(point.y).isEqualTo(2.0);
    }
}
