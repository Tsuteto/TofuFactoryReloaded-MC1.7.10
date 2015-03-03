package tsuteto.tofufactory.util;

public class TFVector
{
    public int x;
    public int y;
    public int z;

    public TFVector(int[] dim)
    {
        if (dim.length != 3)
        {
            throw new RuntimeException("Cannot instantiate a vector with less or more than 3 points.");
        }
        else
        {
            this.x = dim[0];
            this.y = dim[1];
            this.z = dim[2];
        }
    }

    public TFVector(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public TFVector add(TFVector other)
    {
        TFVector result = new TFVector(this.x, this.y, this.z);
        result.x += other.x;
        result.y += other.y;
        result.z += other.z;
        return result;
    }

    public TFVector multiply(float factor)
    {
        TFVector result = new TFVector(this.x, this.y, this.z);
        result.x = (int)((float)result.x * factor);
        result.y = (int)((float)result.y * factor);
        result.z = (int)((float)result.z * factor);
        return result;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        else if (obj == null)
        {
            return false;
        }
        else if (this.getClass() != obj.getClass())
        {
            return false;
        }
        else
        {
            TFVector other = (TFVector)obj;
            return this.x != other.x ? false : (this.y != other.y ? false : this.z == other.z);
        }
    }
}
