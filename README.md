# vshell

> ⚠️ **Project Status:** vshell is under active development. APIs may change and you may encounter bugs or incomplete features.

**vshell** is a programmable, application-controlled shell designed for secure environments where full system shell access is not acceptable.

Instead of exposing a real OS shell, `vshell` lets you define exactly:

* what commands exist
* how they behave

> Think of it as a **virtual shell runtime embedded inside your application**.
> In vshell, a command is simply **application logic exposed through a shell interface**.

---

## 🚀 Why vshell

* **Controlled command surface**
  Only explicitly registered commands are available.

* **Safer SSH access**
  Provide debug access without exposing the host system.

* **Policy-driven execution**
  Commands are implemented in code, so you control behavior.

---

👉 The shell runs **inside your application**, not on the host OS.

---

## 📦 Modules

* **core** → vshell grammar
* **parser** → parsing & AST
* **jvm** → execution engine
* **sample** → SSH server demo with basic commands

---

## ▶️ Running the Sample

The sample module exposes a controlled SSH shell:

* Runs on `127.0.0.1:2222`
* Username/password authentication
* Only registered commands are available

See [CatCommand](sample/src/main/kotlin/com/srilakshmikanthanp/vshell/sample/commands/CatCommand.kt) and [LsCommand](sample/src/main/kotlin/com/srilakshmikanthanp/vshell/sample/commands/LsCommand.kt) for an example command implementation.

```bash
./gradlew --no-daemon :sample:run
```

Then connect:

```bash
ssh -p 2222 "$(whoami)@127.0.0.1"
```

Default password:

```
password
```

---

## 💻 Example Session

```text
user@127.0.0.1:/home/user> ls
core
jvm
sample

user@127.0.0.1:/home/user> cat 'Note.txt'
Note: quotes are required for arguments.

user@127.0.0.1:/home/user> exit
Connection closed.
```

---

## 🎯 When to Use vshell

Use `vshell` when you need:

* controlled SSH access
* safe production debugging
* restricted environments

---
