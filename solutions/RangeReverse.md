# `rangeReverse()` — Step-by-Step Diagram

Example:

```text
start = 2, end = 5
```

The numbers under the values are their original indices.

## Step 1: Find the range

The range starts at index `2` and ends at index `5`.

```mermaid
flowchart LR
    DH["Dummy head"] --> N0["3<br/>index 0"]
    N0 --> N1["5<br/>index 1"]
    N1 --> START["8<br/>index 2<br/>START"]
    START --> N3["7<br/>index 3"]
    N3 --> N4["2<br/>index 4"]
    N4 --> END["1<br/>index 5<br/>END"]
    END --> N6["6<br/>index 6"]
    N6 --> N7["4<br/>index 7"]
    N7 --> N8["2<br/>index 8"]
```

`nodeBeforeStart` points to `5`, `firstNodeInRange` points to `8`, and
`lastNodeInRange` points to `1`.

## Step 2: Disconnect the range

This happens in two small parts.

### Step 2A: Make the main list skip the range

Here, `nodeBeforeStart` is node `5` and `nodeAfterEnd` is node `6`.

```java
nodeBeforeStart.next = nodeAfterEnd;
```

After this line, the arrow from `5` to `8` is replaced by an arrow from `5` to
`6`. The main list now skips the selected range:

```mermaid
flowchart LR
    DH["Dummy head"] --> N0["3"] --> BEFORE["5"] --> AFTER["6"] --> N7["4"] --> N8["2"] --> NULL1["null"]
    FIRST["firstNodeInRange"] -. points to .-> R0["8"] --> R1["7"] --> R2["2"] --> LAST["1"]
    LAST --> AFTER
```

The selected nodes are not completely disconnected yet. Node `1` still points
to node `6`.

### Step 2B: Disconnect the end of the range

```java
lastNodeInRange.next = null;
```

After this line, node `1` points to `null`. Now the selected range and the
remaining list are separate:

```mermaid
flowchart TB
    subgraph REMAINING["Remaining list"]
        direction LR
        DH["Dummy head"] --> N0["3"] --> N1["5"] --> N6["6"] --> N7["4"] --> N8["2"] --> NULL1["null"]
    end

    subgraph RANGE["Disconnected range"]
        direction LR
        FIRST["firstNodeInRange"] -. points to .-> R0["8"] --> R1["7"] --> R2["2"] --> R3["1"] --> NULL2["null"]
    end
```

## Step 3: Reverse the disconnected range

Each node is pointed toward the node before it.

```mermaid
flowchart LR
    R3["1"] --> R2["2"] --> R1["7"] --> R0["8"] --> NULL["null"]
```

After reversal, `previousNode` points to `1`, the first reversed node.
`firstNodeInRange` still points to `8`, which is now the last reversed node.

## Step 4: Put the reversed range after the dummy head

This also happens one line at a time.

### Step 4A: Save the beginning of the remaining list

```java
Node firstRemainingNode = dHead.next;
```

This line does not change any arrows. It makes `firstRemainingNode` point to
node `3`, so we can still find the remaining list after changing `dHead.next`.

```mermaid
flowchart TB
    subgraph REVERSED["Reversed range"]
        direction LR
        PREVIOUS["previousNode"] -. points to .-> R3["1"] --> R2["2"] --> R1["7"] --> R0["8"] --> NULL1["null"]
    end

    subgraph REMAINING["Remaining list"]
        direction LR
        DH["Dummy head"] --> N0["3"] --> N1["5"] --> N6["6"] --> N7["4"] --> N8["2"] --> NULL2["null"]
        FIRST["firstRemainingNode"] -. points to .-> N0
    end
```

### Step 4B: Make the dummy head point to the reversed range

```java
dHead.next = previousNode;
```

After this line, the reversed range is at the front. The remaining nodes are
temporarily separate, but `firstRemainingNode` still points to node `3`.

```mermaid
flowchart TB
    subgraph FRONT["List starting at the dummy head"]
        direction LR
        DH["Dummy head"] --> R3["1"] --> R2["2"] --> R1["7"] --> R0["8"] --> NULL1["null"]
    end

    subgraph SAVED["Saved remaining list"]
        direction LR
        FIRST["firstRemainingNode"] -. points to .-> N0["3"] --> N1["5"] --> N6["6"] --> N7["4"] --> N8["2"] --> NULL2["null"]
    end
```

### Step 4C: Join the two parts

```java
firstNodeInRange.next = firstRemainingNode;
```

After this line, node `8` points to node `3`. The full list is connected:

```mermaid
flowchart LR
    DH["Dummy head"] --> R3["1"] --> R2["2"] --> R1["7"] --> R0["8"]
    R0 --> N0["3"] --> N1["5"] --> N6["6"] --> N7["4"] --> N8["2"]
```

No new node is created. The method only changes the `next` links of the
existing nodes.
