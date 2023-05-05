package cn.wycfight.study.mapper;

import cn.wycfight.study.dto.CarDTO;
import cn.wycfight.study.entity.Car;
import cn.wycfight.study.utils.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CarMapperUnitTest {

    @Test
    public void givenCarEntitytoCar_whenMaps_thenCorrect() {

        Car entity = new Car();
        entity.setId(1);
        entity.setName("Toyota");

        CarDTO carDto = CarMapper.INSTANCE.carToCarDTO(entity);

        assertEquals(carDto.getId(), entity.getId());
        assertEquals(carDto.getName(), entity.getName());
    }

    @Test
    public void StringSplitTest() {
        String password = "中文";
        char[] chars = password.toCharArray();
        String s = new String(chars);
        assertEquals(s, password);
    }

    @Test
    public void dateBetween() {
        int value = (int) getDistanceOfTwoDate(new Date(), DateUtil.stringToDate("2023-09-28"));
        System.out.println(value);
    }

    @Test
    public void cellValue() {
        String role = "副总经理,副董事长,销售工程师";
        int canSetValue = 0;
        if  (role.contains("副总") || role.contains("董事长")) {
            canSetValue = 365;
        } else if (role.contains("经理")) {
            canSetValue = 181;
        } else {
            canSetValue = 90;
        }
        System.out.println(canSetValue);
    }


    public static void main(String[] args) {
        String app = "{\"data\":{\"_dataFrom\":\"waybill\",\"_page_config\":{\"REQUEST_LAYERED_IMAGE\":false},\"adsInfo\":{\"adId\":\"3\",\"advertisementType\":\"PSA\",\"bannerUrl\":\"https://ad-cdn.cainiao.com/img/3/1672122736541.png\",\"miniBannerUrl\":\"https://ad-cdn.cainiao.com/img/3/1672122733813.png\"},\"cpCode\":\"3108002701_1011\",\"needEncrypt\":false,\"orderChannelsType\":\"OTHERS\",\"packageInfo\":{\"currentPackageSequence\":1,\"id\":\"CK-221011-00002-5\",\"items\":[{\"count\":2,\"name\":\"塑壳配电保护\"}],\"totalPackagesCount\":1},\"parent\":true,\"parentWaybillCode\":\"300498892611\",\"recipient\":{\"address\":{\"city\":\"枣庄市\",\"detail\":\"府前东路13号\",\"district\":\"山亭区\",\"province\":\"山东省\"},\"mobile\":\"13888888888\",\"name\":\"测试数据\"},\"routingExtraInfo\":{},\"routingInfo\":{\"consolidation\":{},\"expressFirstCode\":\"539\",\"expressSecondCode\":\"031\",\"expressThirdCode\":\"01\",\"origin\":{\"code\":\"512452\",\"name\":\"吴江八坼镇\"},\"receiveBranch\":{\"code\":\"632007\",\"name\":\"枣庄山亭一部\"},\"sortation\":{},\"startCenter\":{\"code\":\"512000\",\"name\":\"苏州分拨中心\"},\"storeAreaText\":\"苏州-A23,临沂-D12\",\"terminalCenter\":{\"code\":\"539000\",\"name\":\"临沂分拨中心\"}},\"sender\":{\"address\":{\"city\":\"苏州市\",\"detail\":\"江苏省苏州市吴江区太湖新城友谊路188号\",\"district\":\"吴江区\",\"province\":\"江苏省\"},\"mobile\":\"18906246202\",\"name\":\"陈贵友\",\"phone\":\"18906246202\"},\"shippingOption\":{\"services\":{\"SVC-ZTOKY-PAYMENT-TYPE\":{\"value\":\"MONTHLY\"},\"SVC-ZTOKY-DELIVERY-TYPE\":{\"value\":\"SEND\"},\"SVC-ZTOKY-TRANSPORT-TYPE\":{\"value\":\"STANDARD\"},\"SVC-INSURE\":{\"value\":\"2000\"}}},\"waybillCode\":\"30049889261100010001\"},\"signature\":\"MD:XpKq+P+8FIbwpm0FKZN05g==\",\"templateURL\":\"http://cloudprint.cainiao.com/cloudprint/template/getStandardTemplate.json?template_id=101&version=4\",\"ver\":\"waybill_print_secret_version_1\"}";
        JSONObject jsonObject = JSONObject.parseObject(app);
        String appJson = jsonObject.toJSONString();
        System.out.println(appJson);
        JSONObject data = jsonObject.getJSONObject("data");
        String shippingOption = data.getJSONObject("recipient").toJSONString();
        System.out.println(shippingOption);

    }


    public double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }
}
