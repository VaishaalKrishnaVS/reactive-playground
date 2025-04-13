package pg.reactive.reactiveprogramming.section06.assignment;

import pg.reactive.reactiveprogramming.common.Util;

import java.time.Duration;

public class Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.streamData().subscribe(Util.subscriber("inventory"));
        revenueService.streamData().subscribe(Util.subscriber("revenue"));

        Util.sleep(Duration.ofSeconds(20));
    }
}
