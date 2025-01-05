# AESFileProtector
## Overview
**File Encryption App** is a simple Java-based application that provides AES encryption and decryption for files. It ensures secure data handling by using modern encryption standards.

---

## Features
- **Key Generation:** Generates a secure 256-bit AES key and saves it to a file.
- **File Encryption:** Encrypts any file using AES encryption.
- **File Decryption:** Decrypts previously encrypted files back to their original state.

---

## How to Use

### 1. Prerequisites
- Java Development Kit (JDK) version 8 or higher.

### 2. Compilation
Compile the Java source file using the following command:
```bash
javac FileEncryptionApp.java
```

### 3. Execution
Run the program with the following command:
```bash
java FileEncryptionApp
```

### 4. Input Files
- **Key File:** The program generates a secure key file (e.g., `secret.key`) during execution.
- **Input File:** A plaintext file to encrypt (e.g., `test.txt`).
- **Encrypted File:** The output file generated after encryption (e.g., `encrypted.txt`).
- **Decrypted File:** The output file generated after decryption (e.g., `decrypted.txt`).

---

## Example Workflow
1. **Generate a Key:**
   The program will create a `secret.key` file using the `generateKey` method.

2. **Encrypt a File:**
   Input a plaintext file (e.g., `test.txt`) to be encrypted. The encrypted output will be saved as `encrypted.txt`.

3. **Decrypt a File:**
   Use the same `secret.key` file to decrypt the `encrypted.txt` file back to its original form (`decrypted.txt`).

---

## Code Structure
- **`generateKey(String keyFile):`** Generates a secure AES key and saves it to the specified key file.
- **`encryptFile(String inputFile, String outputFile, String keyFile):`** Encrypts the input file and writes the encrypted data to the output file.
- **`decryptFile(String inputFile, String outputFile, String keyFile):`** Decrypts the encrypted file using the key file and writes the original data to the output file.

---

## Important Notes
- The application uses **AES/ECB/PKCS5Padding** mode for encryption.
- **Keep your key file secure!** Losing the key will result in the inability to decrypt your files.
- Avoid using the ECB mode for highly sensitive data as it may reveal patterns in the plaintext. Consider using **CBC (Cipher Block Chaining)** for enhanced security.

---

## License
This project is licensed under the MIT License. Feel free to use, modify, and distribute it as needed.

---

## Disclaimer
This application is intended for educational purposes and is not suitable for production-level encryption without additional security measures.

