package pancake.baker;

import java.util.Map;

import io.grpc.ManagedChannelBuilder;
import pancake.baker.Pancake.Menu;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		var channel = ManagedChannelBuilder.forAddress("localhost", 50051)
				.usePlaintext()
				.build();

		var stub = PancakeBakerServiceGrpc.newBlockingStub(channel);

		var request = BakeRequest.newBuilder()
				.setMenu(Menu.CLASSIC)
				.build();

		var response = stub.bake(request);

		var receipt = Map.of(
			"Chef Name", response.getPancake().getChefName(),
			"Menu", response.getPancake().getMenu(),
			"Technical Score", response.getPancake().getTechnicalScore(),
			"Create Time", response.getPancake().getCreateTime()
		);
		System.out.println(receipt);
	}
}
