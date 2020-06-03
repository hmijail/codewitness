package tech.pegasys.poc.witnesscodeanalysis.functionid;

import org.apache.tuweni.bytes.Bytes;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/**
 * Hold the function id, that will be the key in the Merkle Patricia trie
 * and the leaf information. The format of the leaf information is:
 *
 * Number of blocks
 * For each block: start, length, fragment of code
 *
 */
public class FunctionIdMerklePatriciaTrieLeafData {
  private Bytes functionId;
  private byte[] encodedLeaf;

  public FunctionIdMerklePatriciaTrieLeafData(Bytes functionId, Bytes code, Map<Integer, Integer> blocks) {
    this.functionId = functionId;
    int numBlocks = blocks.size();

    byte[] codeBytes = code.toArray();

    ByteBuffer buf = ByteBuffer.allocate(65000);
    buf.putShort((short)numBlocks);
    for (int start: blocks.keySet()) {
      buf.putShort((short) start);
      int length = blocks.get(start);
      buf.putShort((short) length);
      byte[] codeFragment = new byte[length];
      System.arraycopy(codeBytes, start, codeFragment, 0, length);
      buf.put(codeFragment);
    }
    this.encodedLeaf = Arrays.copyOf(buf.array(), buf.position());
  }

  public Bytes getFunctionId() {
    return functionId;
  }

  public byte[] getEncodedLeaf() {
    return encodedLeaf;
  }

  public BasicBlockWithCode[] getBasicBlocksWithCode() {
    ByteBuffer buf = ByteBuffer.wrap(this.encodedLeaf);
    int numBlocks = ((int) buf.getShort() & 0xffff);

    BasicBlockWithCode[] blocks = new BasicBlockWithCode[numBlocks];

    for (int i=0; i<numBlocks; i++) {
      int start = ((int) buf.getShort() & 0xffff);
      int length = ((int) buf.getShort() & 0xffff);
      byte[] codeFragment = new byte[length];
      buf.get(codeFragment);
      blocks[i] = new BasicBlockWithCode(start, length, Bytes.wrap(codeFragment));
    }
    return blocks;
  }

  @Override
  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append("FunctionId: ");
    buf.append(this.functionId);

    BasicBlockWithCode[] blocks = getBasicBlocksWithCode();
    for (BasicBlockWithCode block: blocks) {
      buf.append(", ");
      buf.append("Start: ");
      buf.append(block.getStart());
      buf.append(", ");
      buf.append("Length: ");
      buf.append(block.getLength());
      buf.append(", ");
      buf.append("Code Fragment: ");
      buf.append(block.getCodeFragment());
    }

    return buf.toString();
  }
}
