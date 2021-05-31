public class Swamp extends TerrainDecorator {

    public Swamp(Terrain terrain) {
        super(terrain);
    }

    @Override
    public int getFuelCost() {
        return terrain.getFuelCost()+20;
    }
}
