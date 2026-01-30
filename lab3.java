import java.util.*;

public class lab3 {
    public static void main(String[] args){
        List<BlockClass> blocks = block_generator();
        blocks.sort(BlockComparator);
        int i = 1;
        System.out.printf("\n\n====Sorted list====\n\n");
        for (BlockClass block : blocks){
            System.out.printf("%d) Size: " + Float.toString(block.getSize()) + " ||  Weight: " + Float.toString(block.getWeight()) + "\n", i);
            i++;
        }
        BlockClass new_block = new BlockClass(3f, 4f, "yellow", "metal", false);
        for (BlockClass block : blocks){
            if (new_block.isEqualTo(block)){
                System.out.printf("\n\n====Identical block found====\n\n");
                System.out.printf("block-1) Size: " + Float.toString(new_block.getSize()) + " ||  Weight: " + Float.toString(new_block.getWeight()) + " || " + new_block.color + " || " + new_block.material + " || " + Boolean.toString(new_block.isDestructible) + "\n");
                System.out.printf("block-2) Size: " + Float.toString(block.getSize()) + " ||  Weight: " + Float.toString(block.getWeight()) + " || " + block.color + " || " + block.material + " || " + Boolean.toString(block.isDestructible) + "\n");
                break;
            }
        }
    }

    public static List<BlockClass> block_generator(){
        List<BlockClass> blocks = new ArrayList<>();
        BlockClass block1 = new BlockClass(1f, 1f, "red", "wood", true);
        BlockClass block2 = new BlockClass(3f, 6f, "blue", "metal", false);
        BlockClass block3 = new BlockClass(2f, 1f, "green", "paper", true);
        BlockClass block4 = new BlockClass(2f, 6f, "black", "rock", false);
        BlockClass block5 = new BlockClass(3f, 4f, "yellow", "metal", false);
        BlockClass block6 = new BlockClass(10f, 8f, "purple", "wood", true);
        BlockClass block7 = new BlockClass(2f, 9f, "brown", "rock", false);

        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
        blocks.add(block6);
        blocks.add(block7);  
        
        return blocks;
    }

    public static Comparator<BlockClass> BlockComparator = new Comparator<BlockClass>(){
        public int compare(BlockClass block1, BlockClass block2){
            if (block1.getSize() < block2.getSize()){
                return 1;
            }
            else if (block1.getSize() > block2.getSize()){
                return -1;
            }
            else if (block1.getWeight() > block2.getWeight()){
                return 1;
            }
            else if (block1.getWeight() < block2.getWeight()){
                return -1;
            }
            else{
                return 0;
            }
        }
    };
}

class BlockClass{
    float size;
    float weight;
    String color;
    String material;
    boolean isDestructible;

    BlockClass(float size, float weight, String color, String material, boolean isDestructible){
        this.size = size;
        this.weight = weight;
        this.color = color;
        this.material = material;
        this.isDestructible = isDestructible;
    }

    public float getSize(){
        return size;
    }

    public float getWeight(){
        return weight;
    }

    public boolean isEqualTo(BlockClass block){
        if (this.size == block.size && this.weight == block.weight && this.color == block.color && this.material == block.material && this.isDestructible == block.isDestructible){
            return true;
        }
        else{
            return false;
        }
    }
}
