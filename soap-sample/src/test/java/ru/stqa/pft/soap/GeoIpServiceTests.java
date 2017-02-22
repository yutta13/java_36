package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by uttabondarenko on 22.02.17.
 */
public class GeoIpServiceTests {

  @Test

  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("79.98.8.1");
    assertEquals(geoIP.getCountryCode(), "RUS");

  }
}

