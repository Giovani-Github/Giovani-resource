
Homepage: http://www.newesttools.com
VMTransferFiles, a tool for VirtualBox is used for transferring files between Host OS and Guest OS via the Explorer context menu.
It also supports Drag'n'Drop from the Windows Host to Windows Guests.

License: GPLv2(http://www.gnu.org/licenses/gpl-2.0.html) 

Requirerements

Host OS: WinXP and above
Guest OS: WinNT4 and above

Please see installation and usage video at: http://www.newesttools.com/vmtransferfiles/vmtr_vid.php


Installation

Definitions

Guest OS  is an operating system that runs in a virtual machine environment
Host OS is the original OS installed on a computer

Inside Host OS run setup_host.exe to install VMTransferFiles Host OS module
Follow Installation Instructions
Note that you should reinstall Host Module after every update of VirtualBox

Run VirtualBox
Start Virtual Machine where you want to use VMTransferFiles features
Make sure to have Settings->General->Advanced->Shared Clipboard set to Bidirectional
Mount GuestSetup.iso (Right-click CD icon at the VirtualBox Window, click "Choose a virtual CD/DVD disk file..." and select GuestSetup.iso )
Inside Guest OS if autorun does not occur, run setup_guest.exe from Guest OS CD drive
Follow Installation Instructions

Usage

Transferring files from Guest OS to Host OS
Inside VirtualBox Guest OS select files and folders which you want to transfer
Right-click the mouse and choose "Copy" from the Explorer context menu (I may also use 'CTRL-C' keyboard shortcut)
Inside Host OS select destination folder, right-click the mouse and choose "Paste files from the Guest OS Clipboard"
Note: 'CTRL-V' keyboard shortcut is not supported here

Transferring files from Host OS to Guest OS is the similar operation
Inside Host OS select files and folders which you want to transfer
Right-click the mouse and choose "Copy" from the Explorer context menu (I may also use 'CTRL-C' keyboard shortcut)
Inside Guest OS select destination folder, right-click the mouse and choose "Paste files from the Host OS Clipboard"

---
End of document

