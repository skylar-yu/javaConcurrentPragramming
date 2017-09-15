package net.jcip.examples.obj.combine;

import java.util.*;
import java.util.concurrent.*;
import net.jcip.annotations.*;
import net.jcip.examples.Point;

/**
 * DelegatingVehicleTracker
 * <p/>
 * Delegating thread safety to a ConcurrentHashMap
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<String, Point>(points);
            //locations��ͨ���������map����ģ���û�н�����������Ա��뱣֤Point�ǲ��ɱ��
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        //���ص��ǲ����޸ĵ�ȴʵʱ�ĳ���λ����ͼ�������ζ��������غ�����λ�÷����˱仯��������ͼ�߳���˵���ǿɼ��ġ�
        return unmodifiableMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("invalid vehicle name: " + id);
    }

    // Alternate version of getLocations (Listing 4.8)
    public Map<String, Point> getLocationsAsStatic() {
        return Collections.unmodifiableMap(
                new HashMap<String, Point>(locations));
    }
}

