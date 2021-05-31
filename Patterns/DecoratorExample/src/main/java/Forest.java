public class Forest extends TerrainDecorator{

    public Forest(Terrain terrain) {
        super(terrain);
    }

    @Override
    public int getFuelCost() {
        return terrain.getFuelCost()+10;
    }
}
